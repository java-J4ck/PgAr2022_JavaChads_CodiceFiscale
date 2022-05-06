package fiscalcode;

import java.util.ArrayList;
/**
 * gestisce la <b>tabella dei codici fiscali</b> e li divide in:
 * <ol>
 * <li>codici spaiati</li> 
 * <li>codici errati</li> 
 * </ol>
 *
 */
public class FiscalCodeManager {

	private  ArrayList<String> allFiscalCode;//tutti i codici fiscali
	private  ArrayList<String> UnmatchedFiscalCode;//codici fiscali spaiati
	
	public FiscalCodeManager(ArrayList<String> allFiscalCode) {//all'inizio i codici spaiati sono uguali alla tabella
		this.allFiscalCode = allFiscalCode;
		this.UnmatchedFiscalCode=new ArrayList<String>(allFiscalCode);
	}
	
	/**
	 * restituisce tutti i codici fiscali errati
	 * @return arrayList dei codici sbagliati
	 */
	public ArrayList<String> getWrongFiscalCode() {
		ArrayList<String> wrongFiscalCode=new ArrayList<String>();
		for(String fiscalCode : allFiscalCode) {
			if(!FiscalCodeOperation.checkFiscalCode(fiscalCode)) wrongFiscalCode.add(fiscalCode);
		}
		return wrongFiscalCode;
	}
	/**
	 * restituisce il codice fiscale se è presente in tabella o assente altrimenti
	 * @param fiscalCode
	 * @return o fiscalCode o ASSENTE
	 */
	public String getFiscalCode(String fiscalCode) {
		String returnedFiscalCode="ASSENTE";
		if(allFiscalCode.contains(fiscalCode)) {
			UnmatchedFiscalCode.remove(fiscalCode);//se il codice fiscale è presente toglie esso dal'array degli spaiati
			returnedFiscalCode=fiscalCode;
		}
		return returnedFiscalCode;
		
	}
	
	public ArrayList<String> getAllFiscalCode() {
		return allFiscalCode;
	}
	/**
	 * restituisce i codici fiscali spaiati
	 * 
	 */
	public ArrayList<String> getUnmatchedFiscalCode() {
		UnmatchedFiscalCode.removeAll(getWrongFiscalCode());//rimuove quelli errati
		return UnmatchedFiscalCode;
	}
	
	
	
	
	
}
