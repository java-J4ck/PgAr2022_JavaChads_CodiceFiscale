package xmlutility;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Person;
import main.Comune;



public class XMLUtility {

	

	
	
	
	
	
	private static final String ATTRIBUTE_NUMERO_MSG = "The root element 'persone' does not containt the attribute 'numero'.";
	private static final String ENCODING_MSG = "The specified file is not encoded in UTF-8.";
	



	public static ArrayList<Comune> readComuni(File cFile) {
		XMLReader xmlr = new XMLReader(cFile);
		ArrayList<Comune> comuni = new ArrayList<Comune>();
		String nome = "";
		String codice = "";
		String last = xmlr.readNext();
		
		
		if(!last.contains("UTF-8")) {
			System.out.println(ENCODING_MSG);
			return null;
		}
		System.out.println(last);  // Stampa la descrizione del file, ovvero il primo evento letto dal lettore xml
		
		
		while(!last.contains("closed")) {
			if(last.contains("comune")) {
				
				for(;;) {
					last = xmlr.readNext();
					if(last.contains("nome")) {
						nome = xmlr.readNext();
						nome = nome.substring(nome.lastIndexOf(":") + 2);
						xmlr.readNext();
						if(nome.length() > 0 && codice.length() > 0) {
							comuni.add(new Comune(nome, codice));
							nome = codice = "";
							break;
						}
					}
					else if(last.contains("codice")) {
						codice = xmlr.readNext();
						codice = codice.substring(codice.lastIndexOf(":") + 2);
						xmlr.readNext();
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
					
					if(last.contains(": nome")) {
						nome = xmlr.readNext();
						//System.out.println(nome);
						nome = nome.substring(nome.lastIndexOf(":") + 2);
						//System.out.println(nome);
						xmlr.readNext();
					}
					else if(last.contains(": cognome")) {
						cognome = xmlr.readNext();
						//System.out.println(cognome);
						cognome = cognome.substring(cognome.lastIndexOf(":") + 2);
						//System.out.println(cognome);
						xmlr.readNext();
					}
					else if(last.contains("sesso")) {
						sesso = xmlr.readNext();
						//System.out.println(sesso);
						sesso = sesso.substring(sesso.lastIndexOf(":") + 2);
						//System.out.println(sesso);
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
