
enum ArabicNumeral{
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);
	
	private int numeral;
	
	ArabicNumeral(int n){
		this.setNumeral(n);
	}
	
	public void setNumeral(int  n) {
		if(n > 10 || n < 0)
			throw new IllegalArgumentException("Введено неверное значение");
		if(n != (this.ordinal() + 1))
			throw new IllegalArgumentException("Введено неверное значение");
		numeral = n;
	}
	
	public int getNumeral() {
		return numeral;
	}
}

