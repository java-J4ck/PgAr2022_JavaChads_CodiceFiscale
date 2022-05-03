package fiscalCode;

import java.util.ArrayList;

public class FiscalCodeManager {

	private  ArrayList<String> allFiscalCode;
	private  ArrayList<String> UnmatchedFiscalCode;
	
	public FiscalCodeManager(ArrayList<String> allFiscalCode) {
		this.allFiscalCode = allFiscalCode;
		this.UnmatchedFiscalCode=new ArrayList<String>(allFiscalCode);
	}
	
	
	public ArrayList<String> getWrongFiscalCode() {
		ArrayList<String> wrongFiscalCode=new ArrayList<String>();
		for(String fiscalCode : allFiscalCode) {
			if(!FiscalCodeOperation.checkFiscalCode(fiscalCode)) wrongFiscalCode.add(fiscalCode);
		}
		return wrongFiscalCode;
	}
	
	public String getFiscalCode(String fiscalCode) {
		String returnedFiscalCode="ASSENTE";
		if(allFiscalCode.contains(fiscalCode)) {
			UnmatchedFiscalCode.remove(fiscalCode);
			returnedFiscalCode=fiscalCode;
		}
		return returnedFiscalCode;
		
	}
	
	public ArrayList<String> getAllFiscalCode() {
		return allFiscalCode;
	}

	public ArrayList<String> getUnmatchedFiscalCode() {
		UnmatchedFiscalCode.removeAll(getWrongFiscalCode());
		return UnmatchedFiscalCode;
	}
	
	
	
	
	
}
