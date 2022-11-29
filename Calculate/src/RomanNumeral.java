
enum RomanNumeral{
	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private int numeral;
	
	RomanNumeral(int n) {
		this.numeral = n;
	};
	
	public int getNumeral() {
		return numeral;
	}
}