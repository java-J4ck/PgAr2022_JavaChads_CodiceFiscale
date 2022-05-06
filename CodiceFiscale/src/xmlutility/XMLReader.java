package xmlutility;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.stream.*;


/**
 * 
 * Classe che implementa un generico lettore di file .xml 
 * 
 * @author I JAVACHADS
 *
 */

public class XMLReader {

	private static final String UNIX_FILE_SEPARATOR = "/";
	private static final String WINDOWS_FILE_SEPARATOR = "\\";
	private static final String FILE_EXTENSION = ".";
	private static final String READING_ERROR_MSG = "[!] Error while reading the file [!]";
	private static final String CLOSING_ERROR_MSG = "[!] Error while closing the document [!]";
	private static final String ERROR_INITIALIZATION_MSG = "[!] Reader initialization failed [!]";
	private static final String CANNOT_READ_MSG = "(Cannot read the specified file)";
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null;
	private String actualElement = "";
	private File inputFile = null;
	
	
	/**
	 * Costruttore della classe.
	 * <p>
	 * Riceve una variabile di tipo <b>File</b> in ingresso, la quale deve contenere il nome del file (o l'intera path che porta al file) xml che si desidera leggere.
	 * Viene controllato se il file specificato puo' effettivamente essere letto e se ha l'estesione corretta (.xml) (in caso contrario stampa un messaggio di errore) e
	 * inizializza il lettore di file xml.
	 * </p>
	 * 
	 * @param inputFile <b>Nome del file</b> .xml che si desidera leggere
	 */
	public XMLReader(File inputFile) {
		if(inputFile.canRead() && getFileExtension(inputFile).equals("xml")) {   // Controllo se il file puo' essere letto e se e' un file xml
			this.inputFile = inputFile;
			try {
				xmlif = XMLInputFactory.newInstance();     
				xmlr = xmlif.createXMLStreamReader(new FileInputStream(inputFile));  // Inizializzo un nuovo oggetto XMLStreamReader
			} 
			catch (Exception e) {
				System.out.println(ERROR_INITIALIZATION_MSG);
				System.out.println(e.getMessage());
			}
		}
		else {
			System.out.println(ERROR_INITIALIZATION_MSG);
			System.out.println(CANNOT_READ_MSG);
		}
		
	}
	
	
	
	
	/**
	 * Metodo per leggere l'intero file xml e stampare a video tutti gli elementi.
	 * <p>
	 * Il metodo legge continuamente il file e per ogni evento letto esegue le relative istruzioni, formattando in maniera adeguata le 
	 * informazioni e stampandole. 
	 * </p> 
	 */
	public void readAndPrintXML() {
		try {
			while(xmlr.hasNext()) {  // Fin tanto che ci sono eventi disponibili, continua a leggere il file
				switch(xmlr.getEventType()) {		
					case XMLStreamConstants.START_DOCUMENT:  // Evento START_DOCUMENT: Inizio del documento
						System.out.println("Start Read Doc " + inputFile.getName());
						break;
					case XMLStreamConstants.START_ELEMENT:	 // Evento START_ELEMENT: Lettura di un tag di apertura		
						System.out.println("Tag : " + xmlr.getLocalName());   
						for (int i=0; i<xmlr.getAttributeCount(); i++)
							System.out.printf("--> Attribute %s : %s\n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));  // Stampa anche tutti gli attributi del tag
						break;
					case XMLStreamConstants.END_ELEMENT:   // Evento END_ELEMENT: Lettura di un tag di chiusura
						System.out.println("End-tag : " + xmlr.getLocalName());
						break;
					case XMLStreamConstants.COMMENT:	// Evento COMMENT: Lettura di un commento
						System.out.println("// " + xmlr.getText());
						break;
					case XMLStreamConstants.CHARACTERS:		// Evento CHARACTERS: Lettura del contenuto di un elemento
						if(xmlr.getText().trim().length() > 0)
							System.out.println("Content : " + xmlr.getText());
						break;
				}
				xmlr.next();   // Leggi il prossimo evento
			}
		} 
		catch (XMLStreamException e) {
			System.out.println(READING_ERROR_MSG);
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Metodo per leggere passo passo ogni elemento del file xml
	 * <p>
	 * Ad ogni invocazione di questo metodo, il lettore legge un singolo evento del file xml e restituisce una stringa descrittiva
	 * di tale evento.
	 * </p>
	 * 
	 * <p>
	 * <table>
	 * 		<tr>
	 * 			<th>EVENTO</th>
	 * 			<th>STRINGA RESTITUITA</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>START_DOCUMENT</td>
	 * 			<td>"Start Read Doc :
	 *				 - Name : *nome file*
	 *				 - Full Path : *percorso completo del file*
	 * 		         - Encoding : *encoding*
	 * 				 - XML Version : *versione xml*"</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>START_ELEMENT</td>
	 * 			<td>"Tag : *nome tag*
	 * 		  		  --> Attribute *nome attributo 1* : *valore attributo 1*	
	 * 		          --> Attribute *nome i-esimo attributo* : *valore i-esimo attributo*"</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>END_ELEMENT</td>
	 * 			<td>"End-Tag : *nome tag*"</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>COMMENT</td>
	 * 			<td>"// Comment : *commento*"</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>CHARACTERS</td>
	 * 			<td>"Content of Tag *nome tag* : *contenuto del tag*"</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>END_DOCUMENT</td>
	 * 			<td>"Doc closed."</td>
	 * 		</tr>
	 * <table>
	 * </p>
	 */
	public String readNext() {
		String s = "";
		try {
			// (N.B. Il metodo hasNext() ritorna false se lo stato corrente di xmlr e' END_DOCUMENT, ovvero la fine del doc)
			if(xmlr.hasNext()) {		// Controlla se ci sono ancora eventi nel documento
				switch(xmlr.getEventType()) {
					case XMLStreamConstants.START_DOCUMENT:
						s = handleSTART_DOCUMENT();
						break;
					case XMLStreamConstants.START_ELEMENT:
						s = handleSTART_ELEMENT();
						break;
					case XMLStreamConstants.END_ELEMENT:
						s = handleEND_ELEMENT();
						break;
					case XMLStreamConstants.COMMENT:
						s = handleCOMMENT();
						break;
					case XMLStreamConstants.CHARACTERS:
						s = handleCHARACTERS();
						break;
				}
				xmlr.next();
			}
			else {
				return handleEND_DOCUMENT();   // Se il file xml non ha piu' contenuto, chiudi il documento
			}
		} 
		catch (XMLStreamException e) {
			System.out.println(READING_ERROR_MSG);
			e.printStackTrace();
		}
		
		return s == null ? this.readNext() : s;

		
	}
	
	
	
	
	
	
	/**
	 * Metodo per gestire l'evento START_ELEMENT.
	 * Ritorna una stringa nel formato:
	 * 		"Tag : *nome tag di apertura*
	 * 		  --> Attribute *nome attributo 1* : *valore attributo 1*	
	 * 		  --> Attribute *nome i-esimo attributo* : *valore i-esimo attribto*"
	 */
	private String handleSTART_ELEMENT() {
		actualElement = xmlr.getLocalName();
		String s = String.format("Tag : %s\n", actualElement);
		for (int i=0; i<xmlr.getAttributeCount(); i++) {
			s = s.concat(String.format(" --> Attribute %s : %s\n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i)));
		}
		return s;
	}
	
	

	/**
	 * Metodo per gestire l'evento END_ELEMENT.
	 * Ritorna una stringa contenente il nome del tag di chiusura.
	 */
	private String handleEND_ELEMENT() {
		String s = String.format("End-Tag : %s", actualElement);
		return s;
	}
	
	
	
	
	/**
	 * Metodo per gestire l'evento CHARACTERS.
	 * Ritorna una stringa contenente il nome del tag e il testo contenuto in esso:
	 * "Content of Tag *nome tag* : *contenuto*"
	 */
	private String handleCHARACTERS() {
		String s = String.format("Content of Tag %s : %s", actualElement, xmlr.getText());
		return xmlr.isWhiteSpace() ? null : s;   // L'XML reader interpreta gli spazi e le tabulazioni fra un tag e l'altro come un evento CHARACTERS, che pero'
	}											 // va scartato in quanto non contiene informazioni
	
	
	
	
	/**
	 * Metodo per gestire l'evento COMMENT.
	 */
	private String handleCOMMENT() {
		String s = String.format("// Comment : %s", xmlr.getText());
		return xmlr.isWhiteSpace() ? null : s;
	}
	
	
	
	/**
	 * Metodo per gestire l'evento START_DOCUMENT.
	 * Ritorna una stringa descrittiva del documento.
	 */
	private String handleSTART_DOCUMENT	() {
		String s = String.format("Start Read Doc : \n"
									+ " - Name : %s\n"
									+ " - Full Path : %s\n"
									+ " - Encoding : %s\n"
									+ " - XML Version : %s", inputFile.getName(), inputFile.getAbsoluteFile(), xmlr.getCharacterEncodingScheme(), xmlr.getVersion());
		return s;
	}
	
	
	
	
	/**
	 * Metodo per gestire l'evento END_DOCUMENT.
	 */
	private String handleEND_DOCUMENT() {
		try {
			xmlr.close();
			return "Doc closed.";
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return CLOSING_ERROR_MSG;
		}
		
	}

	
	


	
	/**
	 * Estrai l'estensione di un file
	 * (Fonte : https://mkyong.com/java/how-to-get-file-extension-in-java/)
	 * 
	 * @param f File di cui si desidera conoscere l'estensione
	 * @return L'estensione di f	
	 */
	private String getFileExtension(File f) {
		String fileName = f.getName();
		String ext = "";
		
		// Trova nel nome del file l'ultima corrispondenza (quindi quella piu' a dx) del carattere "."
		int indexOfLastExtension = fileName.lastIndexOf(FILE_EXTENSION);
		
		// Nel caso venisse fornito un percorso, trova l'ulitmo file separator ("\\" nel caso window, "/" nel caso linux/unix)
        int indexOflastSeparator = Math.max(fileName.lastIndexOf(WINDOWS_FILE_SEPARATOR), fileName.lastIndexOf(UNIX_FILE_SEPARATOR));

        // Se l'indice dell'ultimo "." e' > dell'indice dell'ultimo separatore, ritorna la substring costruita a partire dall'ultimo "."
        // altrimenti ritorna una stringa vuota, in quanto il file specidicato e' una cartella o non ha estensione
        if (indexOfLastExtension > indexOflastSeparator) { 
            return ext = fileName.substring(indexOfLastExtension + 1);
        }
        System.out.println(ext);
        return ext;
	}
	
	
	
	
}
