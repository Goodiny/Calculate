
import java.util.Scanner;

public class Main {

	private static NumeralConvert numeral = new NumeralConvert();
	
	public static void main(String[] args) throws OperandException, NumeralSystemException, OutOfRangeException {

		int result = 0;
		String[] operandArray = {"+", "-", "/", "*"};
		String[] regexpOperandArray = {"\\+", "-", "/", "\\*"};	
		String line = "";
		Scanner in =  new Scanner(System.in);
		System.out.print("Введите выражение: ");
		line = in.nextLine();
		in.close();
		
		assertIsMathematicalString(line);
		assertNotTooManyOperand(line);
		
		int operandIndex = findIndex(operandArray, line);
			
		String[] data = line.split(regexpOperandArray[operandIndex]);
		
		data[0] = data[0].trim();
		data[1] = data[1].trim();
		
		assertNotDifferentNumeralSystem(data[0], data[1]);
//			if(Numeral.isRoman(data[0]) == Numeral.isRoman(data[1])) {
			
		boolean isRoman = false; 
		if(numeral.isRoman(data[0])) {
			isRoman = true; 
		}
		
		int a = 0;
		int b = 0;
		if(!isRoman) {
			
			assertNotOutOfArabicRange(data[0], data[1]);
			
			a = Integer.parseInt(data[0]);
			b = Integer.parseInt(data[1]);
		}
		if(isRoman) {
			a = Integer.parseInt(numeral.romanToInt(data[0]));
			b = Integer.parseInt(numeral.romanToInt(data[1]));
		}
		
		result = calc(a, b, operandArray[operandIndex]);
		
		if(isRoman) assertNotOutOfRomanRange(result);
		
		String S = isRoman ? numeral.intToRoman(result) : String.valueOf(result);
		
		System.out.print(S);
			
//			} else { // two numeral system
//				
//			}
//		}
	}
	
	public static int calc(int a, int b, String operand) {

		int d = 0;
		
		switch(operand) {
			case "+": d = a + b; break;
			case "-": d = a - b; break;
			case "*": d = a * b; break;
			default: d = a / b; break;
		}
		
		return d;
	}
	
	public static boolean inRange(String str) {
		
		int[] numeralRange = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};		
		
		for(int item : numeralRange) 
			if(str.contentEquals(String.valueOf(item))) return true;
		
		return false;
	}
	
	public static void assertIsMathematicalString(String str) throws OperandException {
		
		if(errorOperands(str) == -1) 
			throw new OperandException("Строка не является математической операцией");
	}
	
	public static void assertNotTooManyOperand(String str) throws OperandException {
		
		if(errorOperands(str) == 1)
			throw new OperandException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
	}
	
	public static void assertNotDifferentNumeralSystem(String str1, String str2) throws NumeralSystemException {
		
		if(numeral.isRoman(str1) != numeral.isRoman(str2))
			throw new NumeralSystemException("Используются одновременно разные системы счисления");
	}
	
	public static void assertNotOutOfArabicRange(String str1, String str2) throws OutOfRangeException {
		
		if(!inRange(str1) || !inRange(str2))
			throw new OutOfRangeException("Введеный символ выходит за указаный диапазон арабских символов 1 - 10");
	}
	
	public static void assertNotOutOfRomanRange(int num) throws OutOfRangeException {
		
		if(num < 1) 
			throw new OutOfRangeException("Полученый результат выходит за указанный диапазон значений римских цифр от > I");
	}
	
	private static int countOperands(String s) {
		String[] op = {"+", "-", "/", "*"};
		
		int countOperand = 0;
		for(int i = 0; i < op.length; ++i) 
			countOperand += countMatch(s, op[i]);
		
		return countOperand;
	}
	
//	private static boolean emptyOperands(String s) {
//
//		return countOperands(s) > 0 ? false : true;
//	}
	
	private static int errorOperands(String s) {
		
		int countOperands = countOperands(s);
		
		// out: s == "1" -> -1, s == "1+1*1" -> 1, s == "1+1" -> 0
		return countOperands == 0 ? -1 
				: countOperands > 1 ? 1 : 0;
	}
	
	private static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
	private static int countMatch(String text, String str) {
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
	
	private static int findIndex(String[] arr, String str) {
		
		for(int i = 0; i < arr.length; ++i) {
			if(str.contains(arr[i])) {
				return  i;
			}
		}
		
		return -1;
	}	
}