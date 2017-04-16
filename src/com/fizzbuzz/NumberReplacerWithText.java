package com.fizzbuzz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberReplacerWithText {
	
	private static final String DIGIT_THREE_REPLACER = "lucky";
	private static final String MULTIPLES_OF_THREE_REPLACER = "fizz";
	private static final String MULTIPLES_OF_FIVE_REPLACER = "buzz";
	private static final String MULTIPLES_OF_THREE_FIVE_REPLACER = "fizzbuzz";
	private static final String NUMBER = "number";

	public static void main(String[] args) {
		int[] intArrayInput = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		NumberReplacerWithText numberReplacerWithText = new NumberReplacerWithText();
		numberReplacerWithText.printFizzBuzz(intArrayInput);
		List<String> numberReplacedList = numberReplacerWithText.getNumbersReplacedWithText(intArrayInput);
		Map<String, Long> stats = numberReplacerWithText.getPrintStatistics(numberReplacedList);
		numberReplacerWithText.printStatistics(stats);
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
	
	public Map<String, Long> getPrintStatistics(List<String> itemsToPrint) {
		Map<String, Long> stats = new HashMap<String, Long>();
		long fizzOccurrences = getTextOccurrenceCount(MULTIPLES_OF_THREE_REPLACER, itemsToPrint);
		long buzzOccurrences = getTextOccurrenceCount(MULTIPLES_OF_FIVE_REPLACER, itemsToPrint);
		long fizzBuzzOccurrences = getTextOccurrenceCount(MULTIPLES_OF_THREE_FIVE_REPLACER, itemsToPrint);
		long luckyOccurrences = getTextOccurrenceCount(DIGIT_THREE_REPLACER, itemsToPrint);
		long numberOccurrences = (itemsToPrint.size() - (fizzOccurrences + buzzOccurrences + fizzBuzzOccurrences + luckyOccurrences ));
		
		stats.put(MULTIPLES_OF_THREE_REPLACER, fizzOccurrences);
		stats.put(MULTIPLES_OF_FIVE_REPLACER, buzzOccurrences);
		stats.put(MULTIPLES_OF_THREE_FIVE_REPLACER, fizzBuzzOccurrences);
		stats.put(DIGIT_THREE_REPLACER, luckyOccurrences);
		stats.put(NUMBER, numberOccurrences);
		
		return stats;
	}
 
	
	private void printStatistics(Map<String, Long> itemsToPrint) {
		System.out.println("fizz :" + itemsToPrint.get(MULTIPLES_OF_THREE_REPLACER));
		System.out.println("buzz :" + itemsToPrint.get(MULTIPLES_OF_FIVE_REPLACER));
		System.out.println("fizzbuzz :" + itemsToPrint.get(MULTIPLES_OF_THREE_FIVE_REPLACER));
		System.out.println("lucky :" + itemsToPrint.get(DIGIT_THREE_REPLACER));
		System.out.println("number :" + itemsToPrint.get(NUMBER));
	}

	private Long getTextOccurrenceCount(String string, List<String> itemsToPrint) {
		return itemsToPrint.stream()
							.filter(a -> a.equalsIgnoreCase(string))
							.collect(Collectors.counting());
		
	}
}
