package main;
import java.io.File;
import java.util.ArrayList;

import fiscalcode.FiscalCodeManager;
import xmlutility.XMLUtility;
import xmlutility.XMLWriter;



public class Main {

	public static void main(String[] args) {

		// Inizializza degli oggetti di tipo File che puntano ai file da leggere
		File fileComuni = new File("comuni.xml");
		File fileInputPersone = new File("inputPersone.xml");
		File fileCodiciFiscali= new File("codiciFiscali.xml");
		
		
		// Estrai dai file le informazioni utili e salvale in degli arrayList
		ArrayList<Comune> comuni = new ArrayList<Comune>();
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<String> fiscalCodes = new ArrayList<String>();
		comuni = XMLUtility.readComuni(fileComuni);
		people = XMLUtility.readPerson(fileInputPersone, comuni);
		fiscalCodes = XMLUtility.readFiscalCode(fileCodiciFiscali);
		
		// Scrivi su un nuovo file xml i dati elaborati
		FiscalCodeManager manager = new FiscalCodeManager(fiscalCodes);
		XMLWriter.XmlWrite(people, manager);
		
	}

}
