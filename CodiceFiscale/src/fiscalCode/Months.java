package fiscalCode;
/**
 * enumerazione contentente tutti i <b>mesi</b> con rispettiva <b>lettera</b> e <b>numero di giorni</b>
 * 
 *
 */
public enum Months {
	JENUARY('A',31),
	FEBRUARY('B',28),
	MARCH('C',31),
	APRIL('D',30),
	MAY('E',31),
	JUNE('H',30),
	JULY('L',31),
	AUGUST('M',31),
	SEPTEMBER('P',30),
	OCTOBER('R',31),
	NOVEMBER('S',30),
	DECEMBER('T',31);
	
	private final char monthLetter;
	private  final int daysOfMonth;
	
	private Months(char monthLetter, int daysOfMonth) {
		this.monthLetter = monthLetter;
		this.daysOfMonth = daysOfMonth;
	}

	/**
	 * restituisce la lettera del mese secondo l'algoritmo del codice fiscale
	 * @return lettera del mese
	 */
	public char getMonthLetter() {
		return monthLetter;
	}
	/**
	 * numero dei giorni del mese
	 * @return giorni del mese
	 */
	public int daysOfMonth() {
		return daysOfMonth;
	}
	
	

}
