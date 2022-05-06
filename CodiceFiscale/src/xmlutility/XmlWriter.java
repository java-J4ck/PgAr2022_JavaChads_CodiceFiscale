package xmlutility;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import fiscalcode.FiscalCodeManager;
import fiscalcode.FiscalCodeOperation;
import main.Person;

public class XmlWriter 
{
    public static void XmlWrite(ArrayList<Person>allPerson, FiscalCodeManager fiscalCodeManager) {
    	
    	
    	
    	XMLOutputFactory xmlof = null;
    	XMLStreamWriter xmlw = null;

	
    	
    	try {
			//FileWriter  writer = new FileWriter("OutputFile.xml ");
			
			
			 xmlof = XMLOutputFactory.newInstance();
			 xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Output.xml"), "utf-8");
			 xmlw.writeStartDocument("utf-8", "1.0");
			 

			/* 
			String[] nome = {"Davy", "Giacomo", "Andrea","Sara"}; // esempio di dati da scrivere
			 String[] cognome = {"Pintus", "Baresi", "Gatti","Bordet"};
			 String[] sesso = {"M", "M", "M", "F"}; // esempio di dati da scrivere
			 String[] Comune_nascita = {"k", "R", "M", "C"};*/
			 
			 try { // blocco try per raccogliere eccezioni
	//############################STAMPA PERSONE#############################################################
			  xmlw.writeStartElement("programmaArnaldo"); // scrittura del tag radice <programmaArnaldo>
			  xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
			  xmlw.writeStartElement("OUTPUT");//apri output
			  xmlw.writeStartElement("Persone"); 
			  xmlw.writeAttribute("numero",String.format("%d", allPerson.size()));//apri persone
			  for (int i = 0; i < allPerson.size(); i++) {
				  
				  xmlw.writeStartElement("Nome"); // start elemento nome
				  xmlw.writeAttribute("id", Integer.toString(i)); //scrittura id
				  xmlw.writeCharacters(allPerson.get(i).getName()); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di nome
				  
				  xmlw.writeStartElement("Cognome");//apri cognome
				  xmlw.writeCharacters(allPerson.get(i).getLastName()); // ...e content dato 
				  xmlw.writeEndElement(); // chiudi cognome
				  				  
				  xmlw.writeStartElement("Sesso");//apri sesso
				  xmlw.writeCharacters(String.format("%c", allPerson.get(i).getSex())); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di sesso
				  
				  xmlw.writeStartElement("Comune_nascita");//apri comune di nascita
				  xmlw.writeCharacters(allPerson.get(i).getBirthPlace().getName()); 
				  xmlw.writeEndElement(); // chiusura di comune di nascita
				  
				  xmlw.writeStartElement("Data_di_nascita");//apri data di nascita
				  xmlw.writeCharacters(allPerson.get(i).getBirthDay()); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di data di nascita
				  
				  xmlw.writeStartElement("codice_fiscale");//apri data di nascita
				  String fiscalCode=FiscalCodeOperation.FiscalCodeGenerator(allPerson.get(i));
				  xmlw.writeCharacters(fiscalCodeManager.getFiscalCode(fiscalCode)); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di data di nascita
				  
				  
			  }
			  xmlw.writeEndElement();//chiusura persone
			  xmlw.writeEndElement();//chiusura output	   			  			    
			  xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
			//##################################################################################à  
			  ArrayList<String> invalidCode =fiscalCodeManager.getWrongFiscalCode();
			  ArrayList<String> unMatchedCode =fiscalCodeManager.getUnmatchedFiscalCode();
			  xmlw.writeStartElement("codici");//apri codici
			  xmlw.writeStartElement("invalidi");//apri invalidi
			  xmlw.writeAttribute("numero",String.format("%d", invalidCode.size()));//apri persone
			  for(int i=0;i<invalidCode.size();i++) {
				  xmlw.writeStartElement("codice");//apri codice
				  xmlw.writeCharacters(invalidCode.get(i));
				  xmlw.writeEndElement();//chiusura codice
			  }
			  
			  xmlw.writeEndElement();//chiusura invalidi
			 
			  xmlw.writeStartElement("spaiati");//apri spaiati
			  xmlw.writeAttribute("numero",String.format("%d", unMatchedCode.size()));//apri persone
			  for(int i=0;i<unMatchedCode.size();i++) {
				  xmlw.writeStartElement("codice");//apri codice
				  xmlw.writeCharacters(unMatchedCode.get(i));
				  xmlw.writeEndElement();//chiusura codice
			  }
			  
			  xmlw.writeEndElement();//chiusura spaiati
			 
			  
			  xmlw.writeEndElement();//chiusura codici
			  
			  xmlw.writeEndDocument(); // scrittura della fine del documento
			  xmlw.flush(); // svuota il buffer e procede alla scrittura
			  xmlw.close(); // chiusura del documento e delle risorse impiegate
			 } catch (Exception e) { // se c’è un errore viene eseguita questa parte
			  System.out.println("Errore nella scrittura");
			 }

			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
    	
    	
    		
    	}
   
}