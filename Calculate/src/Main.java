//import java.util.Arrays;

import java.util.Scanner;
//import java.io.IOException;
//import java.lang.ArithmeticException;

public class Main {

	public static void main(String[] args) throws OperandException, NumeralSystemException, OutOfRangeException {
		// TODO Auto-generated method stub
		Scanner in =  new Scanner(System.in);
		String line = new String();
//		
		System.out.print("Введите выражение: ");
		line = in.nextLine();
		
		System.out.println(calc(line));
		in.close();
		
//		System.out.println(calc("1 + 2"));
//		System.out.println(calc("VI / III"));
//		System.out.println(calc("I - II"));
//		System.out.println(calc("I + 1"));
//		System.out.println(calc("1"));
//		System.out.println(calc("1 + 2 + 3"));
	}
	
	public static String calc(String input) throws OperandException, NumeralSystemException, OutOfRangeException{

		int d = 0;
		
		String[] strArr = input.split(" ");
		
		assertIsMathematicalOperandString(input);
		
		assertNotDifferentNumeralSystem(input);
		
		assertNotTooManyOperand(input);
		
		if(isArabic(strArr[0]) && isArabic(strArr[2])) {
			assertNotOutOfRangeOfArabic(input);
			
			d = getCalc(input, true);
			
		}
		if(isRoman(strArr[0]) && isRoman(strArr[2])) {
			
			d = getCalc(input, false);
			
			assertNotOutOfRangeOfRoman(d);
			
			return numeralRoman(d);
			
		}
		
		return String.valueOf(d);
	}
	
	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
	public static int countMatch(String text, String str) {
		if(isEmpty(text) || isEmpty(str)) {
			return 0;
		}
		
		int index = 0, count = 0;
		while(true) {
			index = text.indexOf(str, index);
			if(index != -1) {
				count++;
				index += str.length();
			} 
			else {
				break;
			}
		}
		
		return count;
	}
	
	public static int findIndex(char[] chrs, char chr) {
		
		for(int i = 0; i < chrs.length; ++i) {
			if(chr == chrs[i]) return i;
		}
		
		return -1;
	}
	
	public static int findIndex(RomanNumeral[] items, char chr) {
		
		for(int i = 0; i < items.length; ++i) {
			if(chr == items[i].toString().charAt(0)) return i;
		}
		
		return -1;
	}
	
	public static int findIndex(ArabicNumeral[] items, char chr) {
		
		for(int i = 0; i < items.length; ++i) {
			if(chr == String.valueOf(items[i].ordinal()).charAt(0)) return i;
		}
		
		return -1;
	}
	
	public static RomanNumeral findRoman(RomanNumeral[] items, char chr) {
		
		for(RomanNumeral item : items) {
			if(chr == item.toString().charAt(0)) return item;
		}
		
		return null;
	}
	
	public static boolean isRoman(String str) {
		
		for(char s : str.toUpperCase().toCharArray()) {
			if(findIndex(RomanNumeral.values(), s) != -1) return true;
		}
		
		return false;
		
	}
	
	public static boolean isArabic(String str) {
		
		for(char s : str.toCharArray()) {
			if(findIndex(ArabicNumeral.values(), s) != -1) return true;
		}
		return false;
	}
	
	public static boolean inRange(ArabicNumeral[] range, String str) {
		
		for(ArabicNumeral item : range) {
			if(str.contentEquals(String.valueOf(item.getNumeral()))) return true;
		}
		
		return false;
	}
	
//  // с использованием char[]
//	public static String romanNumeral(String input) {
//		
////		System.out.println(input);
//		
//		input = input.toUpperCase();
//		
//		char[] numerals = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
//		
//		int d = 0;
//		for(int i = 0; i < input.length(); ++i) {
//			
//			int n = 0, num = 0;
//			for(int j = 0; j < numerals.length; ++j) {
//			
//				if(input.charAt(i) == numerals[j]) { // нахождение символа
//					
//					int nextNumeralIndex = -1;
//					if(i < input.length() - 1) 
//						nextNumeralIndex = findIndex(numerals, input.charAt(i+1));	// определение старшего символа
//					
//					// для I, X, C, M
//					if(j % 2 == 0) {
//						num = (int) Math.pow(10, j/2);	// формула вычисления суммы для I, X, C, M
//						if(nextNumeralIndex > j) {	// при позиции перед старшим симоволом
//							d -= num;
//							continue;
//						}
//						d += num;
//					}
//					
//					// для V, L, D
//					if(j % 2 != 0) {
//						num = (int) (Math.pow(5, j-n) * Math.pow(2, j - (n + 1)));	// формула для V, L, D
//						d += num;
//						n++;
//					}
//				}
//				
//			}
//		}
//		
//		return String.valueOf(d);
//	}
	
	public static String romanNumeral(String input) {
		RomanNumeral current;
		
		String[] strArr = input.split(" ");
		
		String S = new String();
		
		for (int j = 0; j < strArr.length; ++j) {
			if(j == 1) {
				S += " " + strArr[j] + " ";
				continue;
			}
			
			int d = 0;
			for(int i= 0; i < strArr[j].length(); ++i) {
				int nextPosition = -1;
	
				// определение старшего символа
				if(i < (strArr[j].length() - 1)) 
					nextPosition = findIndex(RomanNumeral.values(), strArr[j].charAt(i+1));
				
				// определение текущего символа
				current = findRoman(RomanNumeral.values(), strArr[j].charAt(i));
				
				// перед старшим разрядом
				if((current.ordinal() % 2 == 0) && 
						(nextPosition > current.ordinal() && nextPosition <= (current.ordinal() + 2))) {
					d -= current.getNumeral();
					continue;
				}
				d += current.getNumeral();
			}
			S += String.valueOf(d);
		}
		
		return S;
	}
	
	public static String multipleString(String str, int num) {
		
		String S = new String();
		
		for(int i = 0; i < num; ++i) {
			S = S + str;
		}
		
		return S;
	}
	
	public static String numeralRoman(int num) {
		
		String S = new String();
		
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
	
	public static int countOperands(String s) {
		String[] op = new String[] {"+", "-", "/", "*"};
		int opm = 0;
		
		for(int i = 0; i < op.length; ++i) opm += countMatch(s, op[i]);
		
		return opm;
	}
	
	public static boolean emptyOperands(String s) {

		return countOperands(s) > 0 ? false : true;
	}
	
	public static boolean errorOperands(String s) {
		
		if(emptyOperands(s)) return true;
		
		return countOperands(s) == 1 ? false : true;
	}
	
	public static int getCalc(String l, String o, String r) throws OperandException {
		
		int a = Integer.parseInt(l);
		int b = Integer.parseInt(r);
		
		int d = 0;
		
		o = o.trim();
		
		switch(o) {
			case "+": d = a + b; break;
			case "-": d = a - b; break;
			case "/": {
				if(b == 0)
					throw new ArithmeticException("Деление на ноль недопустимо");
				d = a / b; break;
			}
			case "*": d = a * b; break;
			default: throw new OperandException("Строка не явлеятся математической операцией");
		}
		
		return d;
	}
	
	public static int getCalc(String str, boolean  arabic) throws OperandException {
		
		
		if(!arabic)
			str = romanNumeral(str);
		
		String[] strArr = str.split(" ");
		
		int a = Integer.parseInt(strArr[0].trim());
		int b = Integer.parseInt(strArr[2].trim());
		
		int d = 0;
		
		strArr[1] = strArr[1].trim();
		
		switch(strArr[1]) {
			case "+": d = a + b; break;
			case "-": d = a - b; break;
			case "/": {
				if(b == 0)
					throw new ArithmeticException("Деление на ноль недопустимо");
				d = a / b; break;
			}
			case "*": d = a * b; break;
			default: throw new OperandException("Строка не явлеятся математической операцией");
		}
		
		return d;
	}
	
	public static boolean assertIsMathematicalOperandString(String str) throws OperandException {
		
		String[] strArr = str.split(" ");
		
		if(emptyOperands(str) || findIndex(new char[] {'+', '-', '/', '*'}, strArr[1].toCharArray()[0]) == -1) 
			throw new OperandException("Строка не является математической операцией");
		
		return true;
	}
	
	public static boolean assertNotDifferentNumeralSystem(String str) throws NumeralSystemException {
		
		String[] strArr = str.split(" ");
		
		if( (isRoman(strArr[0]) && isArabic(strArr[2])) || (!isRoman(strArr[0]) && !isArabic(strArr[2])) )
			throw new NumeralSystemException("Используются одновременно разные системы счисления");
		
		return true;
	}
	
	public static boolean assertNotTooManyOperand(String str) throws OperandException {
		
		if(errorOperands(str))
			throw new OperandException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

		return true;
	}
	
	public static boolean assertNotOutOfRangeOfArabic(String str) throws OutOfRangeException {
		
		String[] strArr = str.split(" ");
		
		if(!inRange(ArabicNumeral.values(), strArr[0]) || !inRange(ArabicNumeral.values(), strArr[2]))
			throw new OutOfRangeException("Введеный символ выходит за указаный диапазон арабских символов 1 - 10");
		
		return true;
	}
	
	public static boolean assertNotOutOfRangeOfRoman(int num) throws OutOfRangeException {
		
		if(num < 1) 
			throw new OutOfRangeException("Полученый результат выходит за указанный диапазон значений римских цифр от > I");
		
		return true;
	}
	
}


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