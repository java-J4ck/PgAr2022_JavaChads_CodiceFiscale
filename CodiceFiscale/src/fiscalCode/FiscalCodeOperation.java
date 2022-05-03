package fiscalCode;

public class FiscalCodeOperation {
	private static final int END_VALUE_OF_NUMBERS = 57;
	private static final int START_VALUE_OF_NUMBERS = 48;
	private static final int END_VALUE_OF_LETTERS = 90;
	private static final int START_VALUE_OF_LETTERS = 65;
	private static final int FISCAL_CODE_LENGTH = 16;
	private static final Months[] POSSIBLE_MONTH= {Months.JENUARY,Months.FEBRUARY,Months.MARCH,Months.APRIL,Months.MAY,Months.JUNE,Months.JULY,Months.AUGUST,Months.SEPTEMBER,Months.OCTOBER,Months.NOVEMBER,Months.DECEMBER}; 
	private static final String FISCAL_CODE_STRUCTURE="AAAAAA11A11A111A";
	
	
	public static boolean checkFiscalCode(String fiscalCode) {
		boolean found=false;
		int day;
		fiscalCode=fiscalCode.toUpperCase();
		if(fiscalCode.length()!=FISCAL_CODE_LENGTH) return false;
		for(int i=0;i<fiscalCode.length();i++) {
			if(fiscalCode.charAt(i)>=START_VALUE_OF_LETTERS && fiscalCode.charAt(i)<=END_VALUE_OF_LETTERS) {
				
				if(FISCAL_CODE_STRUCTURE.charAt(i)>=START_VALUE_OF_LETTERS && FISCAL_CODE_STRUCTURE.charAt(i)<=END_VALUE_OF_LETTERS) continue;
				else return false;
				
			}
			else if(fiscalCode.charAt(i)>=START_VALUE_OF_NUMBERS && fiscalCode.charAt(i)<=END_VALUE_OF_NUMBERS) {
				
				if(FISCAL_CODE_STRUCTURE.charAt(i)>=START_VALUE_OF_NUMBERS && FISCAL_CODE_STRUCTURE.charAt(i)<=END_VALUE_OF_NUMBERS) continue;
				else return false;
				
			}
			else return false;
		}
		for(Months month: POSSIBLE_MONTH) {
			if(fiscalCode.charAt(8)==month.getMonthLetter()) {
				found=true;
				String dayString=fiscalCode.substring(9, 11);
				if(dayString.startsWith("0"))dayString.replace("0","");
				day=Integer.parseInt(dayString);
				if(!(day>=1 && day<=month.daysOfMonth() || day>=41 && day<=month.daysOfMonth()+30)) return false;
				break;
			}
		}
		if(!found) return false;
		
		
		return true;
	}
}
