import java.util.LinkedHashMap;
import java.util.TreeMap;

class NumeralConvert {
	
	private LinkedHashMap <Character, Integer> romanNumeral = new LinkedHashMap<>();
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
		
		for(char chr: num.toUpperCase().toCharArray()) 
			if (!romanNumeral.containsKey(chr)) return false;
		
		return true;
	}
	
	private int findIndex(String num, int i) {
		
		Object[] keySet = romanNumeral.keySet().toArray();
		
		if (i <= (num.length() - 1)) {
			for(int j = 0; j < keySet.length; ++j)
				if(keySet[j].equals(num.charAt(i))) return j;
		}
		
		return -1;
	}
	
	public String romanToInt(String num) {
		
		int currentIndex;
		
		int d = 0;
		for(int i= 0; i < num.length(); ++i) {
			int nextIndex; 
			
			int arabian = romanNumeral.get(num.charAt(i));
			
			// определение следующего символа
			nextIndex = findIndex(num, i+1);
			
			// определение текущего символа
			currentIndex = findIndex(num, i);
			
			// младший разряд стоит перед старшим разрядом
			if( (currentIndex % 2 == 0) && 
					(nextIndex > currentIndex && nextIndex <= (currentIndex + 2)) ) {
				d -= arabian;
				continue;
			}
			d += arabian;
		}
		
		return String.valueOf(d);
	}
	
	public String intToRoman(int num) {
		
		String S = "";
		
		do {
			int temp = arabicNumeral.floorKey(num);
			S += arabicNumeral.get(temp);
			num -= temp;
		} while(num != 0); 
		
		return S;
	}
	
	private static String multipleString(String str, int num) {
		
		String S = "";
		
		for(int i = 0; i < num; ++i) {
			S = S + str;
		}
		
		return S;
	}
	
	public String intToRoman2(int num) {
		
		String S = "";
		
		int units = num % 10;
		int funits = num % 10 / 5;
		int dozens = num / 10 % 10;
		int fdozens = num / 10 % 10 / 5;
		int hundredths = num / 100 % 10;
		int fhundredths = num / 100 % 10 / 5;
		int thousandths = num / 1000;
		
		// Разряд тысячных
		if(thousandths > 0)
			S += multipleString("M", thousandths);
		
		// Разряд сотых
		if(hundredths == 9) {
			S += "CM";
			hundredths = 0;
			fhundredths = 0;
		}
		
		if(fhundredths > 0) {
			S += "D";
			hundredths -= (hundredths > 0) ? 5 : 0;
		}
		
		if(hundredths == 4) {
			S += "CD";
			hundredths = 0;
	}
		
		if(hundredths > 0)
			S += multipleString("C", hundredths);
		
		
		// Разряд десятых
		if(dozens == 9) {
			S += "XC";
			dozens = 0;
			fdozens = 0;
		}
		
		if(fdozens > 0) {
			S += "L";
			dozens -= (dozens > 0) ? 5 : 0;
		}
		
		if(dozens == 4) {
			S += "XL";
			dozens = 0;
		}
		
		if(dozens > 0)
			S += multipleString("X", dozens);
		
		// Разряд единиц
		if(units == 9) {
			S += "IX";
			units = 0;
			funits = 0;
		}
		
		if(funits > 0) { 
			S += "V";
			units -= (units > 0) ? 5 : 0;
		}
		
		if(units == 4) {
			S += "IV";
			units = 0;
		}
		
		if(units > 0)
			S += multipleString("I", units);
		
		return S;
	}
}

