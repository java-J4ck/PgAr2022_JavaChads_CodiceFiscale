package main;

import fiscalCode.FiscalCodeOperation;

public class Main {

	public static void main(String[] args) {
		Comune comune=new Comune("roncacity","B157");
		Person person=new Person("giacomo","baresi",'M',"2002-09-06",comune,null);
		System.out.println(FiscalCodeOperation.FiscalCodeGenerator(person));
	
	}

}
