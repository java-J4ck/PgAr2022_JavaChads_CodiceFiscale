package fiscalcode;

import java.util.ArrayList;


/**
 * Classe che gestisce la <b>lista dei codici fiscali</b> e li divide in:
 * <ol>
 * <li>Codici spaiati</li> 
 * <li>Codici errati</li> 
 * </ol>
 *
 */

public class FiscalCodeManager {

	private  ArrayList<String> allFiscalCode; // Tutti i codici fiscali
	private  ArrayList<String> UnmatchedFiscalCode; // Codici fiscali spaiati
	
	
	public FiscalCodeManager(ArrayList<String> allFiscalCode) { // all'inizio i codici spaiati sono uguali alla tabella
		this.allFiscalCode = allFiscalCode;
		this.UnmatchedFiscalCode=new ArrayList<String>(allFiscalCode);
	}
	
	
	
	/**
	 * Restituisce tutti i codici fiscali errati
	 * @return arrayList dei codici fiscali sbagliati
	 */
	public ArrayList<String> getWrongFiscalCode() {
		ArrayList<String> wrongFiscalCode=new ArrayList<String>();
		for(String fiscalCode : allFiscalCode) {
			if(!FiscalCodeOperation.checkFiscalCode(fiscalCode)) wrongFiscalCode.add(fiscalCode);
		}
		return wrongFiscalCode;
	}
	
	
	
	
	/**
	 * Controlla se un determinato codice fiscale e' presente nella lista dei codici fiscali. Ritorna il codice fiscale stesso se e' presente nella lista,
	 * la stringa "ASSENTE" in caso contrario
	 * @param fiscalCode Codice fiscale di cui si vuole verificare la presenza nella lista
	 * @return Il codice fiscale (se esso e' presente nella lista) o la stringa "ASSENTE"
	 */
	public String getFiscalCode(String fiscalCode) {
		String returnedFiscalCode="ASSENTE";
		if(allFiscalCode.contains(fiscalCode)) {
			UnmatchedFiscalCode.remove(fiscalCode);//se il codice fiscale è presente toglie esso dal'array degli spaiati
			returnedFiscalCode=fiscalCode;
		}
		return returnedFiscalCode;
		
	}
	
	
	/**
	 * @return La lista dei codici fiscali
	 */
	
	public ArrayList<String> getAllFiscalCode() {
		return allFiscalCode;
	}
	
	

	/**
	 * @return La lista dei codici fiscali spaiati
	 */
	
	public ArrayList<String> getUnmatchedFiscalCode() {
		UnmatchedFiscalCode.removeAll(getWrongFiscalCode());//rimuove quelli errati
		return UnmatchedFiscalCode;
	}
	
	
	
	
	
}
