package xmlutility;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import main.Person;

public class XmlWriter 
{
    public static void XmlWrite(ArrayList<Person>allPerson) {
    	
    	
    	
    	XMLOutputFactory xmlof = null;
    	XMLStreamWriter xmlw = null;

	
    	
    	try {
			//FileWriter  writer = new FileWriter("OutputFile.xml ");
			
			
			 xmlof = XMLOutputFactory.newInstance();
			 xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Output.xml"), "utf-8");
			 xmlw.writeStartDocument("utf-8", "1.0");
			 

			 
			String[] nome = {"Davy", "Giacomo", "Andrea","Sara"}; // esempio di dati da scrivere
			 String[] cognome = {"Pintus", "Baresi", "Gatti","Bordet"};
			 String[] sesso = {"M", "M", "M", "F"}; // esempio di dati da scrivere
			 String[] Comune_nascita = {"k", "R", "M", "C"};
			 
			 try { // blocco try per raccogliere eccezioni
			  xmlw.writeStartElement("programmaArnaldo"); // scrittura del tag radice <programmaArnaldo>
			  xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
			  xmlw.writeStartElement("OUTPUT");//apri output
			  xmlw.writeStartElement("Persone"); xmlw.writeAttribute("numero",String.format("%d", allPerson.size()));//apri persone
			  for (int i = 0; i < 3; i++) {
				  xmlw.writeStartElement("Nome"); // scrittura del tag autore...
				  xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
				  xmlw.writeCharacters(nome[i]); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di </autore>
				  
				  xmlw.writeStartElement("Cognome");
				  //xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
				  xmlw.writeCharacters(cognome[i]); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di </autore>
				  				  
				  xmlw.writeStartElement("Sesso");
				  //xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
				  xmlw.writeCharacters(sesso[i]); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di </autore>
				  
				  xmlw.writeStartElement("Comune_nascita");
				  //xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...
				  xmlw.writeCharacters(Comune_nascita[i]); // ...e content dato 
				  xmlw.writeEndElement(); // chiusura di </autore>
				  
			  }
			  xmlw.writeEndElement();//chiusura persone
			  xmlw.writeEndElement();//chiusura output
			  
			  
			  
			  
			  
			  
			  
			  
			  xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
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