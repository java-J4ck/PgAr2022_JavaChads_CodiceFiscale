package main;



import java.io.File;
import java.util.ArrayList;

import fiscalcode.FiscalCodeManager;
import xmlutility.XMLUtility;
import xmlutility.XmlWriter;


public class Main {

	

	public static void main(String[] args) {
		
	
		
		File f = new File("comuni.xml");
		File i = new File("inputPersone.xml");
		File fiscalCodeFile= new File("codiciFiscali.xml");
		
		ArrayList<Comune> c = new ArrayList<Comune>();
		ArrayList<Person> p = new ArrayList<Person>();
		ArrayList<String> allFiscalCode= new ArrayList<String>();
		c = XMLUtility.readComuni(f);
		p = XMLUtility.readPerson(i, c);
		allFiscalCode = XMLUtility.readFiscalCode(fiscalCodeFile);
		FiscalCodeManager manager= new FiscalCodeManager(allFiscalCode);
		XmlWriter.XmlWrite(p,manager);
		

		
		
	}

}
