package com.fizzbuzz2.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.fizzbuzz.NumberReplacerWithText;

public class NumberReplacerWithTextTester {
	
	private static final String DIGIT_THREE_REPLACER = "lucky";
	private static final String MULTIPLES_OF_THREE_REPLACER = "fizz";
	private static final String MULTIPLES_OF_FIVE_REPLACER = "buzz";
	private static final String MULTIPLES_OF_THREE_FIVE_REPLACER = "fizzbuzz";

	NumberReplacerWithText objectToTest = new NumberReplacerWithText();
	
	@Test
	public void digitThreeIsReplacedByLucky(){
		int[] input = {1,2,3};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2",DIGIT_THREE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void digitThreeIsReplacedByLuckyAndMutiplesOfThreeReplacedByFizz(){
		int[] input = {1,2,3,4,6,7,8,9,11,12,13};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2",DIGIT_THREE_REPLACER,"4",MULTIPLES_OF_THREE_REPLACER,"7", "8", MULTIPLES_OF_THREE_REPLACER,"11", MULTIPLES_OF_THREE_REPLACER , DIGIT_THREE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void digitThreeIsReplacedByLuckyAndMutiplesOfThreeAndFiveReplacedByFizzBuzz(){
		int[] input = {1,2,3,4,6,7,8,9,11,12,13,14,15};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2",DIGIT_THREE_REPLACER,"4",MULTIPLES_OF_THREE_REPLACER,"7", "8", MULTIPLES_OF_THREE_REPLACER,"11" , MULTIPLES_OF_THREE_REPLACER, DIGIT_THREE_REPLACER, "14", MULTIPLES_OF_THREE_FIVE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void postiveMutiplesOfThreeAreDivisibleByThree(){
		int[] mutiplesOfThree = {3,6,9,12,15,18,21};
		for( int i : mutiplesOfThree){
			assertTrue( "Number should be divisible by 3. But " + i + " is not divisible by 3 ", objectToTest.isNumberDivisibleByThree(i));			
		}
	}
	
	@Test
	public void negativeMutiplesOfThreeAreDivisibleByThree(){
		int[] mutiplesOfThree = {-3,-6,-9,-12,-15,-18,-21};
		for( int i : mutiplesOfThree){
			assertTrue( "Number should be divisible by 3. But " + i + " is not divisible by 3 ", objectToTest.isNumberDivisibleByThree(i));			
		}
	}
	
	@Test
	public void positiveNonMutiplesOfThreeAreNotDivisibleByThree(){
		int[] nonMultiplesOfThree = {1,2,4,5,7,8,10};
		for( int i : nonMultiplesOfThree){
			assertFalse("Number should not be divisible by 3. But " +  i + " is divisible by 3 ", objectToTest.isNumberDivisibleByThree(i));			
		}
	}
	
	@Test
	public void negativeNonMutiplesOfThreeAreNotDivisibleByThree(){
		int[] nonMultiplesOfThree = {-1,-2,-4,-5,-7,-8,-10};
		for( int i : nonMultiplesOfThree){
			assertFalse("Number should not be divisible by 3. But " +  i + " is divisible by 3 ", objectToTest.isNumberDivisibleByThree(i));			
		}
	}
	
	@Test
	public void postitveMutiplesOfFiveAreDivisibleByfive(){
		int[] mutiplesOfFive = {5,10,15,20,25,30,35};
		for( int i : mutiplesOfFive){
			assertTrue( "Number should be divisible by 5. But " + i + " is not divisible by 5 ", objectToTest.isNumberDivisibleByFive(i));			
		}
	}
	
	@Test
	public void negativeMutiplesOfFiveAreDivisibleByfive(){
		int[] mutiplesOfFive = {-5,-10,-15,-20,-25,-30,-35};
		for( int i : mutiplesOfFive){
			assertTrue("Number should be divisible by 5. But " +  i + " is not divisible by 5 ", objectToTest.isNumberDivisibleByFive(i));			
		}
	}

	@Test
	public void positiveNonMutiplesOfFiveAreNotDivisibleByfive(){
		int[] nonMultiplesOfFive = {1,2,3,4,6,7,8,9,11};
		for( int i : nonMultiplesOfFive){
			assertFalse("Number should not be divisible by 5. But " +  i + " is divisible by 5 ", objectToTest.isNumberDivisibleByFive(i));			
		}
	}
	
	@Test
	public void negativeNonMutiplesOfFiveAreNotDivisibleByfive(){
		int[] nonMultiplesOfFive = {-1,-2,-3,-4,-6,-7,-8,-9,-11};
		for( int i : nonMultiplesOfFive){
			assertFalse("Number should not be divisible by 5. But " + i + " is divisible by 5 ", objectToTest.isNumberDivisibleByFive(i));			
		}
	}
	
	@Test
	public void multiplesOfThreeAreReplacedByFizzAndDigitThreeByLucky(){
		int[] input = {1,2,3,4,6,7,8,9,11};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2",DIGIT_THREE_REPLACER,"4",MULTIPLES_OF_THREE_REPLACER,"7", "8", MULTIPLES_OF_THREE_REPLACER,"11"));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void multiplesOfFiveAreReplacedByBuzzAndDigitThreeByLucky(){
		int[] input = {1,2,4,5,7,8,10,11,13};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2","4",MULTIPLES_OF_FIVE_REPLACER,"7", "8",MULTIPLES_OF_FIVE_REPLACER,"11",DIGIT_THREE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void multipleOfThreeAndFiveIsReplacedByFizzBuzzAndDigitThreeByLucky(){
		int[] input = {1,2,4,7,8,11,13,14,15};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1","2", "4","7", "8","11",DIGIT_THREE_REPLACER,"14",MULTIPLES_OF_THREE_FIVE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void multiplesOfThreeFiveAreReplacedByFizzBuzzAndDigitThreeByLucky(){
		int[] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList("1", "2", DIGIT_THREE_REPLACER, "4", MULTIPLES_OF_FIVE_REPLACER, MULTIPLES_OF_THREE_REPLACER, "7", "8", MULTIPLES_OF_THREE_REPLACER, MULTIPLES_OF_FIVE_REPLACER, "11", MULTIPLES_OF_THREE_REPLACER, DIGIT_THREE_REPLACER, "14", MULTIPLES_OF_THREE_FIVE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
	
	@Test
	public void zeroIsReplacedByFizzBuzz(){
		int[] input = {0};
		List<String> expectedOutput = new LinkedList<String>(Arrays.asList(MULTIPLES_OF_THREE_FIVE_REPLACER));
		List<String> actualOutput = objectToTest.getNumbersReplacedWithText(input);
		assertTrue(expectedOutput.equals(actualOutput));
	}
}
