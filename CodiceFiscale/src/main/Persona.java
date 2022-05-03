package main;

public class Persona {
	
	private String name;
	private String lastName;
	private char sex;
	private String birthDay;
	private Comune birthPlace;
	private String fiscalCode;
	
	public Persona(String name, String lastName, char sex, String birthDay, Comune birthPlace, String fiscalCode) {
		this.name = name;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.birthPlace = birthPlace;
		this.fiscalCode = fiscalCode;
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
