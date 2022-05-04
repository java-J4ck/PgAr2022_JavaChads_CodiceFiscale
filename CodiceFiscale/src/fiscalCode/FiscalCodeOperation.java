package fiscalCode;

import java.time.Month;
import java.util.Scanner;

import main.Person;

public class FiscalCodeOperation {
	private static final int GIRL_DAY_OFFSET = 40;
	private static final int END_VALUE_OF_NUMBERS = 57;
	private static final int START_VALUE_OF_NUMBERS = 48;
	private static final int END_VALUE_OF_LETTERS = 90;
	private static final int START_VALUE_OF_LETTERS = 65;
	private static final int FISCAL_CODE_LENGTH = 16;
	private static final Months[] POSSIBLE_MONTH= Months.values(); 
	private static final String FISCAL_CODE_STRUCTURE="AAAAAA11A11A111A";
	private static final char[] VOCALI= {'A','E','I','O','U'};
	
	
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
				if(!(day>=1 && day<=month.daysOfMonth() || day>=1+GIRL_DAY_OFFSET && day<=month.daysOfMonth()+GIRL_DAY_OFFSET)) return false;
				break;
			}
		}
		if(!found) return false;
		
		return true;
	}
	
	private static boolean searchArray(char[] array,char character) {
		for(char c: array) {
			if(character==c) return true;
		}
		return false;
	}
	
	
	
	public static String FiscalCodeGenerator(Person person) {
		StringBuffer fiscalCode=new StringBuffer("");
		
		int cnt=0;
//		getDateOfBirhtInInteger(person.getBirthDay());
		String lastName=person.getLastName().toUpperCase();
		if(lastName.length()<3) fiscalCode.append(String.format("%.3s",lastName+"XXX"));
		else {
			for(int i=0;i<lastName.length() && cnt<3;i++) {
				if(!searchArray(VOCALI,lastName.charAt(i))) {
					fiscalCode.append(lastName.charAt(i));
					cnt++;
				}
			}
			for(int i=0;i<lastName.length() && cnt<3;i++) {
				if(searchArray(VOCALI,lastName.charAt(i))) {
					fiscalCode.append(lastName.charAt(i));
					cnt++;
				}
			}
		}
		
		String name=person.getName().toUpperCase();
		cnt=0;
		if(name.length()<3) fiscalCode.append(String.format("%.3s",name+"XXX"));
		else {
			for(int i=0;i<name.length() && cnt<3;i++) {
				if(!searchArray(VOCALI,name.charAt(i))) {
					fiscalCode.append(name.charAt(i));
					cnt++;
				}
			}
			for(int i=0;i<name.length() && cnt<3;i++) {
				if(searchArray(VOCALI,name.charAt(i))) {
					fiscalCode.append(name.charAt(i));
					cnt++;
				}
			}
		}
	
		String date=person.getBirthDay();
		date=date.replace('-', ' ');
		Scanner scan=new Scanner(date);
		String yearOfBirth=scan.next();
		int monthOfBirth=scan.nextInt();
		int dayOfBirth=scan.nextInt();
		scan.close();
		fiscalCode.append(yearOfBirth.substring(yearOfBirth.length()-2, yearOfBirth.length()));
		fiscalCode.append(POSSIBLE_MONTH[monthOfBirth-1].getMonthLetter());
		fiscalCode.append(String.format("%02d", (person.getSex()=='M') ? dayOfBirth:dayOfBirth+GIRL_DAY_OFFSET));
		fiscalCode.append(person.getBirthPlace().getCode());
		fiscalCode.append(ControlCharacter.getControlCharacter(fiscalCode.toString()));
		return fiscalCode.toString();
		
		
	}
	
	//{Months.JENUARY,Months.FEBRUARY,Months.MARCH,Months.APRIL,Months.MAY,Months.JUNE,Months.JULY,Months.AUGUST,Months.SEPTEMBER,Months.OCTOBER,Months.NOVEMBER,Months.DECEMBER};
	
}
