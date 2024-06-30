package com.blazewheeler.statellus;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.blazewheeler.statellus.model.DiscreteMathModel;


import org.jetbrains.dokka.model.doc.B;
import org.jetbrains.dokka.model.doc.Big;
import org.junit.Test;

import java.math.BigDecimal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class DiscreteMathModelUnitTest {

    /**
     * Gives Some UI To The Tests
     * @param testName Name Of Test
     * @param passed Boolean Value If Test Passed Or not
     */
    private static void printResult(String testName, boolean passed) {
        if (passed) {
            System.out.println(testName + ": Test Passed ✅");
        } else {
            System.out.println(testName + ": Test Failed ❌");
        }
    }

    @Test
    public void testFibonacci() {
        // Test case 1: Fibonacci of 0
        int n1 = 0;
        BigInteger result1 = DiscreteMathModel.calculateFibonacci(n1);
        BigInteger expectedResult1 = BigInteger.ZERO;
        printResult("Test Fibonacci sequence 1", result1.equals(expectedResult1));

        int n2 = 5;
        BigInteger result2 = DiscreteMathModel.calculateFibonacci(n2);
        BigInteger expectedResult2 = BigInteger.valueOf(5);
        printResult("Test Fibonacci sequence 2", result2.equals(expectedResult2));

        int n3 = 100;
        BigInteger result3 = DiscreteMathModel.calculateFibonacci(n3);
        BigInteger expectedResult3 = new BigInteger("354224848179261915075");
        printResult("Test Fibonacci sequence 3", result3.equals(expectedResult3));

        int n4 = 499;
        BigInteger result4 = DiscreteMathModel.calculateFibonacci(n4);
        BigInteger expectedResult4 = new BigInteger("86168291600238450732788312165664788095941068326060883324529903470149056115823592713458328176574447204501");
        printResult("Test Fibonacci sequence 4", result4.equals(expectedResult4));

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void testFibonacciSequence() {

        int n1 = 5;
        int n2 = 20;

        ArrayList<BigInteger> result1 = DiscreteMathModel.calculateFibonacciSequence(n1, n2);
        ArrayList<BigInteger> expectedResult1 = new ArrayList<>(Arrays.asList(
                BigInteger.valueOf(5), BigInteger.valueOf(8), BigInteger.valueOf(13), BigInteger.valueOf(21),
                BigInteger.valueOf(34), BigInteger.valueOf(55), BigInteger.valueOf(89), BigInteger.valueOf(144),
                BigInteger.valueOf(233), BigInteger.valueOf(377), BigInteger.valueOf(610), BigInteger.valueOf(987),
                new BigInteger("1597"), new BigInteger("2584"), new BigInteger("4181"),
                new BigInteger("6765")
        ));

        boolean passed1 = result1.size() == expectedResult1.size() && result1.containsAll(expectedResult1) && expectedResult1.containsAll(result1);
        printResult("Fibonacci Sequence Test 1: ", passed1);

        int n3 = 0;
        int n4 = 5;

        ArrayList<BigInteger> result2 = DiscreteMathModel.calculateFibonacciSequence(n3,n4);
        ArrayList<BigInteger> expectedResult2 = new ArrayList<>(Arrays.asList(
                BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(2),
                BigInteger.valueOf(3), BigInteger.valueOf(5)
        ));

        boolean passed2 = result2.size() == expectedResult2.size() && result2.containsAll(expectedResult2) && expectedResult2.containsAll(result2);
        printResult("Fibonacci Sequence Test 2: ", passed2);

        int n5 = 0;
        int n6 = 50;

        ArrayList<BigInteger> result3 = DiscreteMathModel.calculateFibonacciSequence(n5, n6);
        ArrayList<BigInteger> expectedResult3 = new ArrayList<>(Arrays.asList(
                BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.valueOf(2),
                BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8),
                BigInteger.valueOf(13), BigInteger.valueOf(21), BigInteger.valueOf(34),
                BigInteger.valueOf(55), BigInteger.valueOf(89), BigInteger.valueOf(144),
                BigInteger.valueOf(233), BigInteger.valueOf(377), BigInteger.valueOf(610),
                BigInteger.valueOf(987), new BigInteger("1597"), new BigInteger("2584"),
                new BigInteger("4181"), new BigInteger("6765"), new BigInteger("10946"),
                new BigInteger("17711"), new BigInteger("28657"), new BigInteger("46368"),
                new BigInteger("75025"), new BigInteger("121393"), new BigInteger("196418"),
                new BigInteger("317811"), new BigInteger("514229"), new BigInteger("832040"),
                new BigInteger("1346269"), new BigInteger("2178309"), new BigInteger("3524578"),
                new BigInteger("5702887"), new BigInteger("9227465"), new BigInteger("14930352"),
                new BigInteger("24157817"), new BigInteger("39088169"), new BigInteger("63245986"),
                new BigInteger("102334155"), new BigInteger("165580141"), new BigInteger("267914296"),
                new BigInteger("433494437"), new BigInteger("701408733"), new BigInteger("1134903170"),
                new BigInteger("1836311903"), new BigInteger("2971215073"), new BigInteger("4807526976"),
                new BigInteger("7778742049"), new BigInteger("12586269025")
        ));

        boolean passed3 = result3.size() == expectedResult3.size() && result3.containsAll(expectedResult3) && expectedResult3.containsAll(result3);
        printResult("Fibonacci Sequence Test 3: ", passed3);

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);

    }


    @Test
    public void testCalculateSumOfTerms(){


        // Simple Test
        int n1 = 6;
        BigDecimal testOneA1 = BigDecimal.valueOf(4);
        BigDecimal testOneD = BigDecimal.valueOf(4);
        BigDecimal result1 = DiscreteMathModel.calculateSumOfTerms(n1,testOneA1,testOneD);
        BigDecimal expectedResult1 = BigDecimal.valueOf(84);
        printResult("Test Sum Of Terms: 1", result1.equals(expectedResult1));


        // Negative Number Test
        int n2 = 10;
        BigDecimal testTwoA1 = BigDecimal.valueOf(-7);
        BigDecimal testTwoD = BigDecimal.valueOf(1);
        BigDecimal result2 = DiscreteMathModel.calculateSumOfTerms(n2,testTwoA1,testTwoD);
        BigDecimal expectedResult2 = BigDecimal.valueOf(-25);
        printResult("Test Sum Of Terms: 2", result2.equals(expectedResult2));

        // Big Number Test
        int n3 = 10000;
        BigDecimal testThreeA1 = BigDecimal.valueOf(100000);
        BigDecimal testThreeD = BigDecimal.valueOf(500);
        BigDecimal result3 = DiscreteMathModel.calculateSumOfTerms(n3,testThreeA1,testThreeD);
        BigDecimal expectedResult3 = new BigDecimal("25997500000");
        printResult("Test Sum Of Terms: 3", result3.equals(expectedResult3));


        //TODO: Decimal Test

        // Negative Number Test
        int n4 = 10;
        BigDecimal testFourA1 = BigDecimal.valueOf(-7.89);
        BigDecimal testFourD = BigDecimal.valueOf(1.6);
        BigDecimal result4 = DiscreteMathModel.calculateSumOfTerms(n4,testFourA1,testFourD);
        BigDecimal expectedResult4 = BigDecimal.valueOf(-6.9);
        printResult("Test Sum Of Terms: 4", result4.equals(expectedResult4));



        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }




    @Test
    public void testArithmeticProgression() {
        int n = 4;
        BigDecimal a1 = BigDecimal.valueOf(3);
        BigDecimal d = BigDecimal.valueOf(2);

        ArrayList<BigDecimal> result = DiscreteMathModel.calculateArithmeticProgression(n, a1, d);

        ArrayList<BigDecimal> expectedResult = new ArrayList<>(Arrays.asList(
                BigDecimal.valueOf(3), BigDecimal.valueOf(5), BigDecimal.valueOf(7), BigDecimal.valueOf(9)
        ));

        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testGeometricSequence() {
        int n = 12;
        BigDecimal a1 = BigDecimal.valueOf(2);
        BigDecimal r = BigDecimal.valueOf(5);

        ArrayList<BigDecimal> result = DiscreteMathModel.calculateGeometricSequence( a1, r, n);

        ArrayList<BigDecimal> expectedResult = new ArrayList<>(Arrays.asList(
                BigDecimal.valueOf(2),BigDecimal.valueOf(10), BigDecimal.valueOf(50),
                BigDecimal.valueOf(250), BigDecimal.valueOf(1250), BigDecimal.valueOf(6250),
                BigDecimal.valueOf(31250), BigDecimal.valueOf(156250), BigDecimal.valueOf(781250),
                BigDecimal.valueOf(3906250))
        );

        assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testGeometricNthTerm() {
        int n = 12;
        BigDecimal a1 = BigDecimal.valueOf(2);
        BigDecimal r = BigDecimal.valueOf(5);

        BigDecimal result1 = DiscreteMathModel.calculateNthTermOfGeometricSequence(a1, r, n);
        BigDecimal expectedResult1 = BigDecimal.valueOf(97656250);

        assertEquals(expectedResult1, result1);
    }



    @Test
    public void testZScore(){

        // Error handling std must be greater than zero

        // Simple Test
        BigDecimal rawScore = new BigDecimal("5");
        BigDecimal mean = new BigDecimal("3");
        BigDecimal standardDeviation = new BigDecimal("2");
        BigDecimal result1 = DiscreteMathModel.calculateZScore(rawScore, mean, standardDeviation);
        BigDecimal expectedResult1 = BigDecimal.ONE;

        // Zero Test
        BigDecimal rawScore2 = BigDecimal.ZERO;
        BigDecimal mean2 = BigDecimal.ZERO;
        BigDecimal standardDeviation2 = BigDecimal.ONE;
        BigDecimal result2 = DiscreteMathModel.calculateZScore(rawScore2,mean2,standardDeviation2);
        BigDecimal expectedResult2 = BigDecimal.ZERO;

        // Decimal Test
        BigDecimal rawScore3 = new BigDecimal("30");
        BigDecimal mean3 = new BigDecimal("1.25");
        BigDecimal standardDeviation3 = new BigDecimal("20");
        BigDecimal result3 = DiscreteMathModel.calculateZScore(rawScore3,mean3,standardDeviation3);
        BigDecimal expectedResult3 = new BigDecimal("1.4");

        // Big Numbers
        BigDecimal rawScore4 = new BigDecimal("300000000");
        BigDecimal mean4 = new BigDecimal("10000000");
        BigDecimal standardDeviation4 = new BigDecimal("200000");
        BigDecimal result4 = DiscreteMathModel.calculateZScore(rawScore4, mean4,standardDeviation4);
        BigDecimal expectedResult4 = new BigDecimal("1450");

        assertEquals(expectedResult1,result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);

    }


    @Test
    public void testPScore() {


        ArrayList<Double> testarr = DiscreteMathModel.calculateProbabilities(30.0, 1.25, 20);
        System.out.println(testarr);

        System.out.println("P-Value" + DiscreteMathModel.calculatePValue(1.4375));


    }


    //TODO: Write More tests for GeometricNth Term and GeometricSequence
    // TODO: Write Tests For calculateGeometricSumOfTerms
    // TODO: Write Tests for calculateArithmeticSum

}
