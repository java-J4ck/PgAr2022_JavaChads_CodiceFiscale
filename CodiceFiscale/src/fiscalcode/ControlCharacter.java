package fiscalcode;

import java.util.HashMap;
import java.util.Map;

public class ControlCharacter {
	//tabella di conversione per caratteri dispari
	private static final Map<Character,Integer> ODD_ALFANUMERIC_VALUE=new HashMap<Character,Integer>(){{
		put('0',1);put('9',21);put('I',19);put('R',8);
		put('1',0);put('A',1);put('J',21);put('S',12);
		put('2',5);put('B',0);put('K',2);put('T',14);
		put('3',7);put('C',5);put('L',4);put('U',16);
		put('4',9);put('D',7);put('M',18);put('V',10);
		put('5',13);put('E',9);put('N',20);put('W',22);
		put('6',15);put('F',13);put('O',11);put('X',25);
		put('7',17);put('G',15);put('P',3);put('Y',24);
		put('8',19);put('H',17);put('Q',6);put('Z',23);
	}};
	//tabella di conversione per caratteri pari
	private static final Map<Character,Integer> EVEN_ALFANUMERIC_VALUE=new HashMap<Character,Integer>(){{
		put('0',0);put('9',9);put('I',8);put('R',17);
		put('1',1);put('A',0);put('J',9);put('S',18);
		put('2',2);put('B',1);put('K',10);put('T',19);
		put('3',3);put('C',2);put('L',11);put('U',20);
		put('4',4);put('D',3);put('M',12);put('V',21);
		put('5',5);put('E',4);put('N',13);put('W',22);
		put('6',6);put('F',5);put('O',14);put('X',23);
		put('7',7);put('G',6);put('P',15);put('Y',24);
		put('8',8);put('H',7);put('Q',16);put('Z',25);
	}};
	//tabella di conversione per resto
	private static final Map<Integer,Character> REMAINDER_TABLE= new HashMap<Integer,Character> (){{
		put(0,'A');put(7,'H');put(14,'O');put(21,'V');
		put(1,'B');put(8,'I');put(15,'P');put(22,'W');
		put(2,'C');put(9,'J');put(16,'Q');put(23,'X');
		put(3,'D');put(10,'K');put(17,'R');put(24,'Y');
		put(4,'E');put(11,'L');put(18,'S');put(25,'Z');
		put(5,'F');put(12,'M');put(19,'T');
		put(6,'G');put(13,'N');put(20,'U');
		
	}};

	private static final int DIVIDE_CONSTANT=26;
	/**
	 * restituisce il carattere di controllo
	 * @param partialFiscalCode codice fiscale senza carattere di controllo
	 * @return carattere di controllo
	 */
	public static char getControlCharacter(String partialFiscalCode) {
		int sum=0;
		char controlCharacter;
		for(int i=0;i<partialFiscalCode.length();i++) {
			if((i+1)%2==0) sum+=EVEN_ALFANUMERIC_VALUE.get(partialFiscalCode.charAt(i));//se il carattere è pari ne somma il valore secondo la tabella
			else sum+=ODD_ALFANUMERIC_VALUE.get(partialFiscalCode.charAt(i));//lo stesso se il carattere è dispari
		}
		controlCharacter=REMAINDER_TABLE.get(sum%DIVIDE_CONSTANT);//prende il carattere dalla tabella del resto
		return controlCharacter;
	}
}
