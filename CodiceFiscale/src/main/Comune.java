package main;
/**
 * classe che rappresenta il comune
 * 
 *
 */
public class Comune {
	private String name;
	private String code;//codice catastale
	
	public Comune() {}
	
	public Comune(String name, String code) {
		
		this.name = name;
		this.code = code;
	}
	/**
	 * prende il nome
	 * @return nome del comune
	 */
	public String getName() {
		return name;
	}
	/**
	 * prende il codice catastale
	 * @return codice catastale
	 */
	public String getCode() {
		return code;
	}
	
}
