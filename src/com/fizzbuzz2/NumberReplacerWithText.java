package com.fizzbuzz2;

import java.util.LinkedList;
import java.util.List;

public class NumberReplacerWithText {
	
	private static final String DIGIT_THREE_REPLACER = "lucky";
	private static final String MULTIPLES_OF_THREE_REPLACER = "fizz";
	private static final String MULTIPLES_OF_FIVE_REPLACER = "buzz";
	private static final String MULTIPLES_OF_THREE_FIVE_REPLACER = "fizzbuzz";

	public static void main(String[] args) {
		int[] intArrayInput = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		new NumberReplacerWithText().printFizzBuzz(intArrayInput);
	}
	
	public void printFizzBuzz(int[] intArrayInput){
		
		List<String> itemsToPrint = getNumbersReplacedWithText(intArrayInput);
		
		for(String toPrint : itemsToPrint ){
			System.out.println(toPrint);
		}
	}

	public List<String> getNumbersReplacedWithText(int[] intArrayInput) {
		
		String numberReplacer = "";
		List<String> nosReplacedWithTextList = new LinkedList<String>();
		
		for(int i : intArrayInput){
			
			numberReplacer = String.valueOf(i);
			
			if(doesTheNumberContainDigitThree(i)){
				numberReplacer = DIGIT_THREE_REPLACER;				
			}else if(isNumberDivisibleByThreeAndFive(i)){
				numberReplacer = MULTIPLES_OF_THREE_FIVE_REPLACER;				
			}else if(isNumberDivisibleByThree(i)){
				numberReplacer = MULTIPLES_OF_THREE_REPLACER;				
			}else if(isNumberDivisibleByFive(i)){
				numberReplacer = MULTIPLES_OF_FIVE_REPLACER;				
			}
			nosReplacedWithTextList.add(numberReplacer);
		}
		
		return nosReplacedWithTextList;
	}
	
	public boolean doesTheNumberContainDigitThree(int i){
		String numberString = String.valueOf(i);
		return numberString.contains("3");
	}
	
	public boolean isNumberDivisibleByThreeAndFive(int i){
		return i%3 == 0 && i%5 == 0;
	}
	
	public boolean isNumberDivisibleByThree(int i){
		return i%3 == 0;
	}
	
	public boolean isNumberDivisibleByFive(int i){
		return i%5 == 0;
	}
}
