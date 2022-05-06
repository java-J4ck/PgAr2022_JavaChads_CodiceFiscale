package fiscalcode;


import java.util.Scanner;

import main.Person;

public class FiscalCodeOperation {
	private static final int MONTH_LETTER_POSITION = 8;//posizione della lettera del mese
	private static final int GIRL_DAY_OFFSET = 40;//offset del giorno di nascita se si è donna
	private static final int END_VALUE_OF_NUMBERS = 57;//estremo superiore dei valori dei numeri nella tabella ascii
	private static final int START_VALUE_OF_NUMBERS = 48;//estremo inferiore dei valori dei numeri nella tabella ascii
	private static final int END_VALUE_OF_LETTERS = 90;//estremo superiore dei valori delle lettere nella tabella ascii
	private static final int START_VALUE_OF_LETTERS = 65;//estremo inferiore dei valori delle lettere nella tabella ascii
	private static final int FISCAL_CODE_LENGTH = 16;//lunghezza del codice fiscale
	private static final Months[] POSSIBLE_MONTH= Months.values();//array contenente tutti i mesi 
	private static final String FISCAL_CODE_STRUCTURE="AAAAAA11A11A111A";//struttura del codice fiscale(tramite A e 1 dice come sono disposti cifre e lettere)
	private static final char[] VOCALI= {'A','E','I','O','U'};//array contenente vocali
	
	/**
	 * <ul>controlla se il codice fiscale è corretto tramite:
	 * <li>lunghezza del codice(deve avere 16 caratteri)</li>
  	 * <li>disposizione dei caratteri(il posto dei numeri e le lettere deve essere sempre uguale)</li>
  	 * <li>verifica se il giorno di nascita è possibile</li></ul>
  	 *  
	 * @param fiscalCode codice fiscale
	 * @return true se corretto false altrimenti
	 */
	public static boolean checkFiscalCode(String fiscalCode) {
		boolean found=false;
		int day;
		fiscalCode=fiscalCode.toUpperCase();
		if(fiscalCode.length()!=FISCAL_CODE_LENGTH) return false;//controlla la lunghezza
		//controlla la disposizione di numeri e lettere
		for(int i=0;i<fiscalCode.length();i++) {//cicla per tutto il codice fiscale
			if(fiscalCode.charAt(i)>=START_VALUE_OF_LETTERS && fiscalCode.charAt(i)<=END_VALUE_OF_LETTERS) {//controlla se il primo carattere è una lettera
				
				if(FISCAL_CODE_STRUCTURE.charAt(i)>=START_VALUE_OF_LETTERS && FISCAL_CODE_STRUCTURE.charAt(i)<=END_VALUE_OF_LETTERS) continue;//se anche il carattere della struttura del codice fiscale è una lettera allora continua
				else return false;//se invece non combacia allora il codice fiscale è incorretto
				
			}
			//stessa cosa con i numeri
			else if(fiscalCode.charAt(i)>=START_VALUE_OF_NUMBERS && fiscalCode.charAt(i)<=END_VALUE_OF_NUMBERS) {
				
				if(FISCAL_CODE_STRUCTURE.charAt(i)>=START_VALUE_OF_NUMBERS && FISCAL_CODE_STRUCTURE.charAt(i)<=END_VALUE_OF_NUMBERS) continue;
				else return false;
				
			}
			else return false;
		}
		//controlla la validità del giorno
		for(Months month: POSSIBLE_MONTH) {//cicla per tutti i mesi
			if(fiscalCode.charAt(MONTH_LETTER_POSITION)==month.getMonthLetter()) {//trova il mese di nascita
				found=true;//scrive che l'ha trovato
				String dayString=fiscalCode.substring(9, 11);//prende il giorno e lo trasforma in intero
				if(dayString.startsWith("0"))dayString.replace("0","");
				day=Integer.parseInt(dayString);
				if(!(day>=1 && day<=month.daysOfMonth() || day>=1+GIRL_DAY_OFFSET && day<=month.daysOfMonth()+GIRL_DAY_OFFSET)) return false;//controlla se il giorno è compreso nei giorni del mese
				break;
			}
		}
		if(!found) return false;//se non trova il mese vuol dire che la lettera è sbagliata e quindi anche il codice fiscale
		String paritalFiscalCode=fiscalCode.substring(0, fiscalCode.length()-1);//controlla se corrispondono i caratteri di controllo
		if(fiscalCode.charAt(fiscalCode.length()-1)!=ControlCharacter.getControlCharacter(paritalFiscalCode)) return false;// se non corrispondono il codice fiscale è scorretto
		//controllo nome e cognome
		for(int i=1;i<6;i++) {//controlla che le consonanti e le vocali nel nome e cognome siano corrette
			if (fiscalCode.charAt(i)!='X' && i!=3)
			if (searchArray(VOCALI,fiscalCode.charAt(i-1)) && !searchArray(VOCALI,fiscalCode.charAt(i)))return false;// non ci può essere una vocale prima di una consonante
		}
		
		return true;//se si arriva fino a questo punto allora il codice fiscale è giusto
	}
	
	private static boolean searchArray(char[] array,char character) {//cerca un carattere in un array
		for(char c: array) {
			if(character==c) return true;
		}
		return false;
	}
	
	
	/**
	 * classe che genera un codice fiscale
	 * @param person persona contenente i dati utili
	 * @return il codice fiscale
	 */
	public static String FiscalCodeGenerator(Person person) {
		StringBuffer fiscalCode=new StringBuffer("");
		
		int cnt=0;
		//algoritmo per generare le tre lettere del cognome
		String lastName=person.getLastName().toUpperCase();
		if(lastName.length()<3) fiscalCode.append(String.format("%.3s",lastName+"XXX"));//se la lunghezza del nome è minore di 3 riempie con X
		else {
			for(int i=0;i<lastName.length() && cnt<3;i++) {//prende le consonanti 
				if(!searchArray(VOCALI,lastName.charAt(i))) {
					fiscalCode.append(lastName.charAt(i));
					cnt++;
				}
			}
			for(int i=0;i<lastName.length() && cnt<3;i++) {//prende le vocali se non ci sono 3 consonanti
				if(searchArray(VOCALI,lastName.charAt(i))) {
					fiscalCode.append(lastName.charAt(i));
					cnt++;
				}
			}
		}
		//algoritmo per generare le tre lettere del nome(uguale al cognome)
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
		//elabora la parte della data di nascita
		String date=person.getBirthDay();
		date=date.replace('-', ' ');
		Scanner scan=new Scanner(date);//con lo scanner prendo i dati utili dalla stringa del tipo YYYY-MM-DD
		String yearOfBirth=scan.next();//prendo l'anno come stringa
		int monthOfBirth=scan.nextInt();//prendo il mese come intero
		int dayOfBirth=scan.nextInt();//prendo il giorno come intero
		scan.close();
		fiscalCode.append(yearOfBirth.substring(yearOfBirth.length()-2, yearOfBirth.length()));//prendo le ultime due cifre dell'anno
		fiscalCode.append(POSSIBLE_MONTH[monthOfBirth-1].getMonthLetter());//prendo la lettera del mese utilizzando il numero del mese come indice per l'array dei mesi
		fiscalCode.append(String.format("%02d", (person.getSex()=='M') ? dayOfBirth:dayOfBirth+GIRL_DAY_OFFSET));//genera la stringa per il giorno
		fiscalCode.append(person.getBirthPlace().getCode());//prende il codice del comune
		fiscalCode.append(ControlCharacter.getControlCharacter(fiscalCode.toString()));//genera il carattere di controllo
		return fiscalCode.toString();//restituisce il codice fiscale in stringa
		
		
	}
	
	
}
