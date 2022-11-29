import java.util.TreeMap;

class NumeralConvert {
	
	private TreeMap <Character, Integer> romanNumeral = new TreeMap <>();
	private TreeMap <Integer, String> arabicNumeral = new TreeMap <>(); 
	
	NumeralConvert(){
		romanNumeral.put('I', 1);
		romanNumeral.put('V', 5);
		romanNumeral.put('X', 10);
		romanNumeral.put('L', 50);
		romanNumeral.put('C', 100);
		romanNumeral.put('D', 500);
		romanNumeral.put('M', 1000);
		
		arabicNumeral.put(1, "I");
		arabicNumeral.put(4, "IV");
		arabicNumeral.put(5, "V");
		arabicNumeral.put(8, "IX");
		arabicNumeral.put(10, "X");
		arabicNumeral.put(40, "XL");
		arabicNumeral.put(50, "L");
		arabicNumeral.put(90, "XC");
		arabicNumeral.put(100, "C");
		arabicNumeral.put(400, "CD");
		arabicNumeral.put(500, "D");
		arabicNumeral.put(900, "CM");
		arabicNumeral.put(1000, "M");
	}
	
	
	public boolean isRoman(String num) {
		
		return romanNumeral.containsKey(num.charAt(0));
	}
}

