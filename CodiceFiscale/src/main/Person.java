package main;


/**
 * Classe che rappresenta un generico essere umano
 * 
 */

public class Person {
	
	private String name;
	private String lastName;
	private char sex;
	private String birthDay; // data di nascita in formato YYYY-MM-DD
	private Comune birthPlace; // comune di nascita
	private String fiscalCode;
	
	/**
	 * Costruttore della classe
	 * @param name Nome della persona
	 * @param lastName Cognome della persona
	 * @param sex Sesso della persona
	 * @param birthDay Data di nascita della persona
	 * @param birthPlace Luogo di nascita della persona (oggetto di tipo <b>Comune</b>)
	 */
	public Person(String name, String lastName, char sex, String birthDay, Comune birthPlace) {
		this.name = name;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.birthPlace = birthPlace;
	}


	/**
	 * @return Nome della persona
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return Cognome della persona
	 */
	public String getLastName() {
		return lastName;
	}

	
	/**
	 * @return Sesso della persona
	 */
	public char getSex() {
		return sex;
	}


	/**
	 * @return La data di nascita della persona
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @return Il luogo di nascita della persona
	 */
	public Comune getBirthPlace() {
		return birthPlace;
	}


	/**
	 * @return Il codice fiscale della persona
	 */
	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * Setter per il codice fiscale della persona
	 * @param fiscalCode Il codice fiscale
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	
	


	
	

	
	

}
