package main;

/**
 * Classe che rappresenta un generico comune 
 *
 */

public class Comune {
	private String name;
	private String code; // Codice catastale
	
	public Comune() {}
	
	public Comune(String name, String code) {
		
		this.name = name;
		this.code = code;
	}
	
	/**
	 * @return Nome del comune
	 */
	public String getName() {
		return name;
	}
	/** 
	 * @return Codice catastale
	 */
	public String getCode() {
		return code;
	}
	
}
