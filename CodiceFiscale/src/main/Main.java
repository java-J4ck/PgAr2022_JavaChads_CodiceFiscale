package main;



import java.io.File;
import java.util.ArrayList;

import xmlutility.XMLUtility;
import xmlutility.XmlWriter;


public class Main {

	

	public static void main(String[] args) {
		
		/**
		Comune comune=new Comune("roncacity","B157");
		Person person=new Person("giacomo","baresi",'M',"2002-09-06",comune);
		System.out.println(FiscalCodeOperation.FiscalCodeGenerator(person));
		**/
		
		File f = new File("comuni.xml");
		File i = new File("inputPersone.xml");
		
		ArrayList<Comune> c = new ArrayList<Comune>();
		ArrayList<Person> p = new ArrayList<Person>();
		
		c = XMLUtility.readComuni(f);
		p = XMLUtility.readPerson(i, c);
		
		XmlWriter.XmlWrite(null);
		
		/*
		for(Comune k : c) {
			System.out.printf("Nome: %s --> Codice: %s\n", k.getName(), k.getCode());
		}
		*/
	
		
	/*	for(Person q : p) {
			System.out.println("Nome : " + q.getName());
			System.out.println("Cognome : " + q.getLastName());
			System.out.println("Sesso : " + q.getSex());
			System.out.println("Comune di nascita : " + q.getBirthPlace().getName());
			System.out.println("Data di nascita : " + q.getBirthDay());
		}*/
		
		
	}

}
