package main;
/**
 * classe che rappresenta una persona
 * 
 *
 */
public class Person {
	
	private String name;
	private String lastName;
	private char sex;
	private String birthDay;//data di nascita in formato YYYY-MM-DD
	private Comune birthPlace;//comune di nascita
	private String fiscalCode;
	
	public Person(String name, String lastName, char sex, String birthDay, Comune birthPlace) {
		this.name = name;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.birthPlace = birthPlace;
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public char getSex() {
		return sex;
	}
	
	public String getBirthDay() {
		return birthDay;
	}
	
	public Comune getBirthPlace() {
		return birthPlace;
	}
	

}
