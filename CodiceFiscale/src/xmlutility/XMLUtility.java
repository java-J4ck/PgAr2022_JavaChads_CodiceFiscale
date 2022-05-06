package xmlutility;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Person;
import main.Comune;


/**
 * Classe contenente metodi per l'estrazione delle informazioni dai 3 file xml.
 * Per ricavare le informazioni, vengono interpretate le stringhe restituite dalla classe {@link XMLReader}. 
 */


public class XMLUtility {
	
	private static final String ENCODING_MSG = "The specified file is not encoded in UTF-8.";
	
	/**
	 * Metodo per costruire l'arrayList di comuni. Estrare ed interpreta le informazioni da un file xml formattato in uno specifico modo:
	 * 
	 * <comuni numero="NUMERO COMUNI">
     * 		<comune>
     *			<nome>NOME COMUNE</nome>
     *			<codice>CODICE CATASTALE</codice>
     * 		</comune>
	 * </comuni>
	 * 
	 * @param cFile File xml da leggere (di tipo <b>File</b>)
	 * @return arraList di comuni con i dati letti dal file
	 */
	public static ArrayList<Comune> readComuni(File cFile) {
		XMLReader xmlr = new XMLReader(cFile);		// Inizializza un nuovo lettore
		ArrayList<Comune> comuni = new ArrayList<Comune>();
		String nome = "";
		String codice = "";
		String last = xmlr.readNext();  // Leggi il primo evento
		
		
		if(!last.contains("UTF-8")) {		// Controlla se l'encoding dell'xml e' corretto 
			System.out.println(ENCODING_MSG);
			return null;
		}
		System.out.println(last);  // Stampa la descrizione del file, ovvero il primo evento letto dal lettore xml
		
		
		while(!last.contains("closed")) {    // Finche' il lettore non restituisce la stringa relativa all'evento END_DOCUMENT, continua a leggere
			
			if(last.contains("comune")) {	
				
				for(;;) {
					last = xmlr.readNext();
					if(last.contains("nome")) {  
						nome = xmlr.readNext();
						nome = nome.substring(nome.lastIndexOf(":") + 2);	// Estrai dalla stringa restituita dal lettore il nome del comune	
						xmlr.readNext();   // Scarta l'evento successivo, ovvero quello del tag di chiusura
						if(nome.length() > 0 && codice.length() > 0) {	// Se sono stati gia' acquisiti sia nome che codice del comune, inizializza un nuovo comune
							comuni.add(new Comune(nome, codice));       // e aggiungilo all'arrayList. Infine esci dal ciclo for
							nome = codice = "";
							break;
						}
					}
					else if(last.contains("codice")) {
						codice = xmlr.readNext();
						codice = codice.substring(codice.lastIndexOf(":") + 2);    // Estrai dalla stringa restituita dal lettore il codice del comune
						xmlr.readNext();    // Scarta l'evento successivo, ovvero quello del tag di chiusura
						if(nome.length() > 0 && codice.length() > 0) {
							comuni.add(new Comune(nome, codice));
							nome = codice = "";
							break;
						}
					}
					else {
						break;
					}
				}
				
			}
			
			last = xmlr.readNext();
			
		}
		
		return comuni;
		
	}
	
	
	
	
	
	
	/**
	 * Metodo per costruire l'arrayList di persone. Estrare ed interpreta le informazioni da un file xml formattato in uno specifico modo:
	 * 
	 * <persone numero="NUMERO PERSONE">
     *		<persona id="0">
     * 			<nome>NOME</nome>
     *			<cognome>COGNOME</cognome>
     *			<sesso>SESSO</sesso>
     *			<comune_nascita>NOME COMUNE DI NASCITA</comune_nascita>
     *			<data_nascita>DATA DI NASCITA</data_nascita>
     * 		</persona>
	 * </persone>
	 * 
	 * @param pFile File xml da leggere (di tipo <b>File</b>)
	 * @param comuni arrayList dei comuni, necessario per reperire i codici catastali
	 * @return arrayList di persone con i dati letti dal file
	 */
	
	public static ArrayList<Person> readPerson(File pFile, ArrayList<Comune> comuni) {
		XMLReader xmlr = new XMLReader(pFile);
		ArrayList<Person> people = new ArrayList<Person>();
		String nome = "", cognome = "", data_nascita = "", sesso = "", comune_nascita = "";
		Comune comune = new Comune();
		String last = xmlr.readNext();
		
		if(!last.contains("UTF-8")) {
			System.out.println(ENCODING_MSG);
			return null;
		}
		System.out.println(last);  // Stampa la descrizione del file, ovvero il primo evento letto dal lettore xml
		
		/*
		last = xmlr.readNext();
		Pattern personNumberPattern = Pattern.compile("^.*numero : (\\d+)$");
		Matcher m = personNumberPattern.matcher(last.split("\\r?\\n")[1]);
		if(m.find()) {	// Controlla se nel root tag "persone" e' presente l'attributo contenete il numero di persone nella lista
			num = (int) Integer.parseInt(m.group(1));
		}
		else {
			System.out.println(ATTRIBUTE_NUMERO_MSG);
			return null;
		}
		*/
		
		while(!last.contains("closed")) {
				
			last = xmlr.readNext();
			
			if(last.contains("persona")) {
				last = xmlr.readNext();
				
				for(;;) {
					
					if(last.contains(": nome")) {    // Per evitare errori, controlla se la stringa restituita dal lettore contiene ": nome" (e non solo "nome"
						nome = xmlr.readNext();		 //	in quanto "nome" e' contenuta anche in "cognome")
						nome = nome.substring(nome.lastIndexOf(":") + 2);
						xmlr.readNext();
					}
					else if(last.contains(": cognome")) {
						cognome = xmlr.readNext();
						cognome = cognome.substring(cognome.lastIndexOf(":") + 2);
						xmlr.readNext();
					}
					else if(last.contains("sesso")) {
						sesso = xmlr.readNext();
						sesso = sesso.substring(sesso.lastIndexOf(":") + 2);
						xmlr.readNext();
					}
					else if(last.contains("comune_nascita")) {
						comune_nascita = xmlr.readNext();
						comune_nascita = comune_nascita.substring(comune_nascita.lastIndexOf(":") + 2);
						for(Comune c : comuni) {
							if(c.getName().equals(comune_nascita)) {
								comune = c;
							}
						}
						xmlr.readNext();
					}
					else if(last.contains("data_nascita")) {
						data_nascita = xmlr.readNext();
						data_nascita = data_nascita.substring(data_nascita.lastIndexOf(":") + 2); 
						xmlr.readNext();
						break;
					}
					
					last = xmlr.readNext();
					
				}
				
				people.add(new Person(nome, cognome, sesso.charAt(0), data_nascita, comune));
					
			}
			
		}
		
		return people;
			
	}
		

	
	
	/**
	 * Metodo per costruire l'arrayList di codici fiscali. Estrare ed interpreta le informazioni da un file xml formattato in uno specifico modo:
	 * 
	 * <codici size="NUMERO CODICI FISCALI">
     *		<codice>CODICE FISCALE</codice>
     *		<codice>CODICE FISCALE</codice>
     * 		<codice>CODICE FISCALE</codice>
     * </codici>
	 * 
	 * @param fcFile File xml da leggere (di tipo <b>File</b>)
	 * @return arrayList di stringe contente i codici fiscali letti dal file
	 */
	
	public static ArrayList<String> readFiscalCode(File fcFile) {
		XMLReader xmlr = new XMLReader(fcFile);
		ArrayList<String> fiscalCodes = new ArrayList<String>();
		String last = xmlr.readNext();
		if(!last.contains("UTF-8")) {
			System.out.println(ENCODING_MSG);
			return null;
		}
		System.out.println(last);  // Stampa la descrizione del file, ovvero il primo evento letto dal file xml
		
		while(!last.contains("closed")) {     // Continua a leggere finche' il metodo readNext() restituisce il messaggio di fine documento
			if(last.contains("Content of Tag codice")) {
				fiscalCodes.add(last.substring(last.lastIndexOf(":") + 2));  // Estrai il codice fiscale dalla stringa restituita dal metodo readNext()
			}
			last = xmlr.readNext();
		}
		return fiscalCodes;
			
		
	}
	
	
	
	
}
