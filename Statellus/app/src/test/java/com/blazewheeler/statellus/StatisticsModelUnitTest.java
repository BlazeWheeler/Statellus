package com.blazewheeler.statellus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.math.BigDecimal.valueOf;

import com.blazewheeler.statellus.utils.Pair;

import com.blazewheeler.statellus.model.StatisticsModel;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Testing Class
 * No Functions require tests for Empty Lists, lists where n.size() must be > 1, non numeric chars,
 * non comma formatted lists, etc. These exceptions are caught and handled through the UI.
 * Hence, the functions will never be called with non-formatted data.
 */

public class StatisticsModelUnitTest {

    static DecimalFormat df = new DecimalFormat("#.#");

    //Array size = 150, negative and positive numbers, no same elements
    ArrayList<Float> bigFloatArray = new ArrayList<>(Arrays.asList(
            1.2f, -3.5f, 6.8f, -9.1f, 12.3f, -15.6f, 18.9f, -22.4f, 25.7f, -29.0f,
            32.1f, -35.4f, 38.7f, -42.0f, 45.3f, -48.6f, 51.9f, -55.2f, 58.5f, -61.8f,
            65.1f, -68.4f, 71.7f, -75.0f, 78.3f, -81.6f, 84.9f, -88.2f, 91.5f, -94.8f,
            98.1f, -101.4f, 104.7f, -108.0f, 111.3f, -114.6f, 117.9f, -121.2f, 124.5f,
            -127.8f, 131.1f, -134.4f, 137.7f, -141.0f, 144.3f, -147.6f, 150.9f, -154.2f,
            157.5f, -160.8f, 164.1f, -167.4f, 170.7f, -174.0f, 177.3f, -180.6f, 183.9f,
            -187.2f, 190.5f, -193.8f, 197.1f, -200.4f, 203.7f, -207.0f, 210.3f, -213.6f,
            216.9f, -220.2f, 223.5f, -226.8f, 230.1f, -233.4f, 236.7f, -240.0f, 243.3f,
            -246.6f, 249.9f, -253.2f, 256.5f, -259.8f, 263.1f, -266.4f, 269.7f, -273.0f));

    ArrayList<BigDecimal>bigDecimalArray = new ArrayList<>(Arrays.asList(
            new BigDecimal("1.2"), new BigDecimal("-3.5"), new BigDecimal("6.8"), new BigDecimal("-9.1"),
            new BigDecimal("12.3"), new BigDecimal("-15.6"), new BigDecimal("18.9"), new BigDecimal("-22.4"),
            new BigDecimal("25.7"), new BigDecimal("-29.0"), new BigDecimal("32.1"), new BigDecimal("-35.4"),
            new BigDecimal("38.7"), new BigDecimal("-42.0"), new BigDecimal("45.3"), new BigDecimal("-48.6"),
            new BigDecimal("51.9"), new BigDecimal("-55.2"), new BigDecimal("58.5"), new BigDecimal("-61.8"),
            new BigDecimal("65.1"), new BigDecimal("-68.4"), new BigDecimal("71.7"), new BigDecimal("-75.0"),
            new BigDecimal("78.3"), new BigDecimal("-81.6"), new BigDecimal("84.9"), new BigDecimal("-88.2"),
            new BigDecimal("91.5"), new BigDecimal("-94.8"), new BigDecimal("98.1"), new BigDecimal("-101.4"),
            new BigDecimal("104.7"), new BigDecimal("-108.0"), new BigDecimal("111.3"), new BigDecimal("-114.6"),
            new BigDecimal("117.9"), new BigDecimal("-121.2"), new BigDecimal("124.5"), new BigDecimal("-127.8"),
            new BigDecimal("131.1"), new BigDecimal("-134.4"), new BigDecimal("137.7"), new BigDecimal("-141.0"),
            new BigDecimal("144.3"), new BigDecimal("-147.6"), new BigDecimal("150.9"), new BigDecimal("-154.2"),
            new BigDecimal("157.5"), new BigDecimal("-160.8"), new BigDecimal("164.1"), new BigDecimal("-167.4"),
            new BigDecimal("170.7"), new BigDecimal("-174.0"), new BigDecimal("177.3"), new BigDecimal("-180.6"),
            new BigDecimal("183.9"), new BigDecimal("-187.2"), new BigDecimal("190.5"), new BigDecimal("-193.8"),
            new BigDecimal("197.1"), new BigDecimal("-200.4"), new BigDecimal("203.7"), new BigDecimal("-207.0"),
            new BigDecimal("210.3"), new BigDecimal("-213.6"), new BigDecimal("216.9"), new BigDecimal("-220.2"),
            new BigDecimal("223.5"), new BigDecimal("-226.8"), new BigDecimal("230.1"), new BigDecimal("-233.4"),
            new BigDecimal("236.7"), new BigDecimal("-240.0"), new BigDecimal("243.3"), new BigDecimal("-246.6"),
            new BigDecimal("249.9"), new BigDecimal("-253.2"), new BigDecimal("256.5"), new BigDecimal("-259.8"),
            new BigDecimal("263.1"), new BigDecimal("-266.4"), new BigDecimal("269.7"), new BigDecimal("-273.0")
    ));

    ArrayList<Float> bigNumsArray = new ArrayList<>(Arrays.asList(100000000f,200000000f,500000000f));
    ArrayList<Float> bigNumsArray2 = new ArrayList<>(Arrays.asList(100000000f,200000000f,300000000f,500000000f));

    ArrayList<Float> negativeFloats = new ArrayList<>(Arrays.asList(
            100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
            60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
            -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
            110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
            -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
            150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));

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

    // Helper method to generate a large set for testing
    private ArrayList<Float> generateLargeSet() {
        ArrayList<Float> largeSet = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            largeSet.add(random.nextFloat());
        }
        return largeSet;
    }


    @Test
    public void testCalculateMean() {
        System.out.println();

        ArrayList<Float> testData1 = bigNumsArray;
        BigDecimal result1 = StatisticsModel.calcBigMean(testData1);
        BigDecimal expectedResult1 = valueOf(266666666.7 );
        printResult("Test calc mean 1", result1.equals(expectedResult1));

        // negative number, zero, postive and negative test
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calcBigMean(testData2);
        BigDecimal expectedResult2 = valueOf(0);
        printResult("Test calc mean 2", result2.equals(expectedResult2));

        // array size of one test
        ArrayList<Float> testData3 = new ArrayList<>(Collections.singletonList(0.0f));
        BigDecimal result3 = StatisticsModel.calcBigMean(testData3);
        BigDecimal expectedResult3 = valueOf(0);
        printResult("Test calc mean 3", result3.equals(expectedResult3));

        // Same number array test
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calcBigMean(testData4);
        BigDecimal expectedResult4 = valueOf(3);
        printResult("Test calc mean 4", result4.equals(expectedResult4));

        // bigger sized numbers
        ArrayList<Float> testData5 = bigFloatArray;
        BigDecimal result5 = StatisticsModel.calcBigMean(testData5);
        BigDecimal expectedResult5 = valueOf(-1.6);
        printResult("Test calc mean 5", result5.equals(expectedResult5));


        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalculateMedian() {
        System.out.println();

        // Big Numbers Odd Test
        ArrayList<Float> testData1 = bigNumsArray;
        BigDecimal result1 = StatisticsModel.calculateBigMedian(testData1);
        BigDecimal expectedResult1 = valueOf(200000000);
        printResult("Test calc median 1", result1.equals(expectedResult1));

        // Simple Even Test
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(1.0f, 4.0f, 3.0f, 2.0f));
        BigDecimal result2 = StatisticsModel.calculateBigMedian(testData2);
        BigDecimal expectedResult2 = valueOf(2.5);
        printResult("Test calc median 2", result2.equals(expectedResult2));

        // Negative Number Test
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(-2.0f, -3.0f, -1.0f, -5.0f, -4.0f));
        BigDecimal result3 = StatisticsModel.calculateBigMedian(testData3);
        BigDecimal expectedResult3 = valueOf(-3);
        printResult("Test calc median 3", result3.equals(expectedResult3));

        // Elements Of Same Value Test
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(2.0f, 2.0f, 2.0f, 2.0f, 2.0f));
        BigDecimal result4 = StatisticsModel.calculateBigMedian(testData4);
        BigDecimal expectedResult4 = valueOf(2);
        printResult("Test calc median 4", result4.equals(expectedResult4));

        // Array Of Large Size
        ArrayList<Float> testData5 = bigFloatArray;
        BigDecimal result5 = StatisticsModel.calculateBigMedian(testData5);
        BigDecimal expectedResult5 = valueOf(-1.1);
        printResult("Test calc median 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalculateMode() {
        System.out.println();

        // Simple MultiModal List
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        List<BigDecimal> result1 = StatisticsModel.calculateBigMode(testData1);
        List<BigDecimal> expectedResult1 = Arrays.asList(valueOf(1), valueOf(2), valueOf(3), valueOf(4), valueOf(5));
        // Passed Boolean Is Used Because We Use BigDecimal List Return Value Rather Than Single Values Accounting For MutliModal Input Data
        boolean passed1 = result1.size() == expectedResult1.size() && result1.containsAll(expectedResult1) && expectedResult1.containsAll(result1);
        printResult("Test calc mode 1", passed1);

        // Big MultiModal List where no elements repeat
        ArrayList<Float> testData2 = bigNumsArray;
        // Elements repeat so testData2 == expectedResult2
        List<BigDecimal> result2 = StatisticsModel.calculateBigMode(testData2);
        List<BigDecimal> expectedResult2 = Arrays.asList(valueOf(100000000), valueOf(200000000), valueOf(500000000));
        boolean passed2 = result2.size() == expectedResult2.size() && result2.containsAll(expectedResult2) && expectedResult2.containsAll(result2);
        printResult("Test calc mode 2", passed2);

        // Big MultiModal List where no elements repeat
        ArrayList<Float> testData3 = bigFloatArray;
        // Elements repeat so testData2 == expectedResult2
        List<BigDecimal> result3 = StatisticsModel.calculateBigMode(testData3);
        List<BigDecimal> expectedResult3 = new ArrayList<>();

        // Format array with correct Modals - Input Data Is A MultiModal list of no alike elements
        for (int i = 0; i < bigDecimalArray.size(); i++) {
            expectedResult3.add(new BigDecimal(df.format(bigDecimalArray.get(i))));
        }
        // Sort Output As Done By Statistics Model
        Collections.sort(expectedResult3);
        boolean passed3 = result3.size() == expectedResult3.size() && result3.containsAll(expectedResult3) && expectedResult3.containsAll(result3);
        printResult("Test calc mode 3", passed3);

        // Singleton Data Input Test
        ArrayList<Float> testData4 = new ArrayList<>(Collections.singletonList(0.0f));
        BigDecimal result4 = StatisticsModel.calcBigMean(testData4);
        BigDecimal expectedResult4 = valueOf(0);
        printResult("Test calc mode 4", result4.equals(expectedResult4));

        // Input With Negative Data Testing
        ArrayList<Float> testData5 = negativeFloats;
        List<BigDecimal> result5 = StatisticsModel.calculateBigMode(testData5);
        List<BigDecimal> expectedResult5 = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(-60.1)));
        printResult("Test calc mode 4", result5.equals(expectedResult5));

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalculateSampleVariance() {
        System.out.println();

        Boolean isPopulation = false;
        // Simple Test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateVariance(testData1,isPopulation);
        BigDecimal expectedResult1 = BigDecimal.valueOf(2.5);
        printResult("Test calc sample variance 1", result1.equals(expectedResult1));

        // Test With Big Input Data
        ArrayList<Float> testData2 = bigFloatArray;
        BigDecimal result2 = StatisticsModel.calculateVariance(testData2, isPopulation);
        BigDecimal expectedResult2 = BigDecimal.valueOf(25210.1); // Calculated manually
        printResult("Test calc  sample variance 2", result2.equals(expectedResult2));

        // Test Positive And Negative Input Data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                                                                   60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                                                                  -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                                                                   110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                                                                   -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                                                                   150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));

        BigDecimal result3 = StatisticsModel.calculateVariance(testData3, isPopulation);
        BigDecimal expectedResult3 = BigDecimal.valueOf(7116.9); // Assuming we know the expected result from some other calculation
        printResult("Test calc sample variance 3", result3.equals(expectedResult3));

        // List of elements of the same value Where Variance Will Equal 0
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateVariance(testData4, isPopulation);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0);
        printResult("Test calc sample variance 4", result4.equals(expectedResult4));

        //Test With Large Number Input Data
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateVariance(testData5, isPopulation);
        BigDecimal expectedResult5 = new BigDecimal("43333333333333333.3");
        printResult("Test calc sample variance 5", result5.equals(expectedResult5));

        // Test With Input Data Of All Zeros
        ArrayList<Float> testData6 = new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        BigDecimal result6 = StatisticsModel.calculateVariance(testData6, isPopulation);
        BigDecimal expectedResult6 = valueOf(0);
        printResult("Test calc sample variance 6", result6.equals(expectedResult6));

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
        assertEquals(expectedResult6, result6);
    }


    @Test
    public void testCalculatePopulationVariance() {
        System.out.println();

        Boolean isPopulation = true;
        // Simple Test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateVariance(testData1,isPopulation);
        BigDecimal expectedResult1 = BigDecimal.valueOf(2);
        printResult("Test calc population variance 1", result1.equals(expectedResult1));

        // Test With Big Input Data
        ArrayList<Float> testData2 = bigFloatArray;
        BigDecimal result2 = StatisticsModel.calculateVariance(testData2, isPopulation);
        BigDecimal expectedResult2 = BigDecimal.valueOf(24910); // Calculated manually
        printResult("Test calc  sample variance 2", result2.equals(expectedResult2));

        // Test Positive And Negative Input Data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));

        BigDecimal result3 = StatisticsModel.calculateVariance(testData3, isPopulation);
        BigDecimal expectedResult3 = BigDecimal.valueOf(6879.7); // Assuming we know the expected result from some other calculation
        printResult("Test calc sample variance 3", result3.equals(expectedResult3));

        // List of elements of the same value Where Variance Will Equal 0
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateVariance(testData4, isPopulation);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0);
        printResult("Test calc sample variance 4", result4.equals(expectedResult4));

        //Test With Large Number Input Data
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateVariance(testData5, isPopulation);
        BigDecimal expectedResult5 = new BigDecimal("28888888888888888.9");
        printResult("Test calc sample variance 5", result5.equals(expectedResult5));

        // Test With Input Data Of All Zeros
        ArrayList<Float> testData6 = new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        BigDecimal result6 = StatisticsModel.calculateVariance(testData6, isPopulation);
        BigDecimal expectedResult6 = valueOf(0);
        printResult("Test calc sample variance 6", result6.equals(expectedResult6));

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
        assertEquals(expectedResult6, result6);
    }

    @Test
    public void testCalculateSampleStandardDeviation() {
        System.out.println();
        Boolean isPopulation = false;
        // Simple Test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateStandardDeviation(testData1, isPopulation);
        BigDecimal expectedResult1 = BigDecimal.valueOf(1.6); // Calculated manually
        printResult("Test calc sample standard deviation 1", result1.equals(expectedResult1));

        // Test With Negative, Positive, And Zero Values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateStandardDeviation(testData2,isPopulation);
        BigDecimal expectedResult2 = BigDecimal.valueOf(1.9); // Calculated manually
        printResult("Test calc sample standard deviation 2", result2.equals(expectedResult2) );

        // Another Test With Negative And Positive Input Data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateStandardDeviation(testData3, isPopulation);
        BigDecimal expectedResult3 = BigDecimal.valueOf(84.4); // Assuming we know the expected result from some other calculation
        printResult("Test calc sample standard deviation 3", result3.equals(expectedResult3));

        // Test Where Expected Result Would Be A Zero Value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateStandardDeviation(testData4, isPopulation);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0);
        printResult("Test calc sample standard deviation 4", result4.equals(expectedResult4));

        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateStandardDeviation(testData5, isPopulation);
        BigDecimal expectedResult5 = BigDecimal.valueOf(208166599.9);
        printResult("Test calc sample standard deviation 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testCalculatePopulationStandardDeviation() {
        System.out.println();
        Boolean isPopulation = true;
        // Simple Test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateStandardDeviation(testData1, isPopulation);
        BigDecimal expectedResult1 = BigDecimal.valueOf(1.4); // Calculated manually
        printResult("Test calc population standard deviation 1", result1.equals(expectedResult1));

        // Test With Negative, Positive, And Zero Values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateStandardDeviation(testData2,isPopulation);
        BigDecimal expectedResult2 = BigDecimal.valueOf(1.7); // Calculated manually
        printResult("Test calc population standard deviation 2", result2.equals(expectedResult2) );

        // Another Test With Negative And Positive Input Data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateStandardDeviation(testData3, isPopulation);
        BigDecimal expectedResult3 = BigDecimal.valueOf(82.9); // Assuming we know the expected result from some other calculation
        printResult("Test calc population standard deviation 3", result3.equals(expectedResult3));

        // Test Where Expected Result Would Be A Zero Value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateStandardDeviation(testData4, isPopulation);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0);
        printResult("Test calc population standard deviation 4", result4.equals(expectedResult4));

        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateStandardDeviation(testData5, isPopulation);
        BigDecimal expectedResult5 = new BigDecimal("169967317.1");
        printResult("Test calc population standard deviation 5", result5.equals(expectedResult5));


        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testCalculateMax() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateMax(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(5);
        printResult("Test calculate max 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateMax(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(2.5f);
        printResult("Test calculate max 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateMax(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(170.8);
        printResult("Test calculate max 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateMax(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(3);
        printResult("Test calculate max 4", result4.equals(expectedResult4));

        // Test 5: Large number input test
        // Test 5: Large number input test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateMax(testData5);
        BigDecimal expectedResult5 = new BigDecimal("500000000");
        printResult("Test calculate inner quartile 5", result5.equals(expectedResult5));
        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalculateMin() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateMin(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(1);
        printResult("Test calculate min 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateMin(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(-2.5f);
        printResult("Test calculate min 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateMin(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(-90.9);
        printResult("Test calculate min 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateMin(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(3);
        printResult("Test calculate min 4", result4.equals(expectedResult4));

        // Test 5: Large number input test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateMin(testData5);
        BigDecimal expectedResult5 = new BigDecimal("100000000");
        printResult("Test calculate inner quartile 5", result5.equals(expectedResult5));
        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    //UI does not allow users to enter Data of n < 4
    @Test
    public void testCalculateInnerQuartile() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateInnerQuartile(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(1.5f);
        printResult("Test calculate inner quartile 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateInnerQuartile(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(-1.8);
        printResult("Test calculate inner quartile 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateInnerQuartile(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(-50.8);
        printResult("Test calculate inner quartile 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateInnerQuartile(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(3);
        printResult("Test calculate inner quartile 4", result4.equals(expectedResult4));



        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray2;
        BigDecimal result5 = StatisticsModel.calculateInnerQuartile(testData5);
        BigDecimal expectedResult5 = new BigDecimal("150000000");
        printResult("Test calculate inner quartile 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testCalculateThirdQuartile() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(19.0f, 21.0f, 23.0f, 20.0f, 23.0f, 27.0f, 25.0f, 24.0f, 31.0f));
        BigDecimal result1 = StatisticsModel.calculateThirdQuartile(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(26);
        printResult("Test calculate third quartile 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateThirdQuartile(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(1.8);
        printResult("Test calculate third quartile 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateThirdQuartile(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(100.2);
        printResult("Test calculate third quartile 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateThirdQuartile(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(3);
        printResult("Test calculate third quartile 4", result4.equals(expectedResult4));

        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray2;
        BigDecimal result5 = StatisticsModel.calculateThirdQuartile(testData5);
        BigDecimal expectedResult5 = new BigDecimal("400000000");
        printResult("Test calculate third quartile 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalcInterQuartileRange() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(19.0f, 21.0f, 23.0f, 20.0f, 23.0f, 27.0f, 25.0f, 24.0f, 31.0f));
        BigDecimal result1 = StatisticsModel.calcInterQuartileRange(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(5.5); // Adjust this according to your implementation
        printResult("Test calculate interquartile range 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calcInterQuartileRange(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(3.6); // Adjust this according to your implementation
        printResult("Test calculate interquartile range 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calcInterQuartileRange(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(151); // Adjust this according to your implementation
        printResult("Test calculate interquartile range 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calcInterQuartileRange(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0); // Adjust this according to your implementation
        printResult("Test calculate interquartile range 4", result4.equals(expectedResult4));

        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray2;
        BigDecimal result5 = StatisticsModel.calcInterQuartileRange(testData5);
        BigDecimal expectedResult5 = new BigDecimal("250000000"); // Adjust this according to your implementation
        printResult("Test calculate interquartile range 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testSetIntersection() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> setA1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        ArrayList<Float> setB1 = new ArrayList<>(Arrays.asList(4.0f, 5.0f, 6.0f, 7.0f, 8.0f));
        ArrayList<Float> result1 = StatisticsModel.setIntersection(setA1, setB1);
        ArrayList<Float> expectedResult1 = new ArrayList<>(Arrays.asList(4.0f, 5.0f));
        printResult("Test set intersection 1", result1.equals(expectedResult1));

        // Test 2: Test with empty sets
        ArrayList<Float> setA2 = new ArrayList<>();
        ArrayList<Float> setB2 = new ArrayList<>();
        ArrayList<Float> result2 = StatisticsModel.setIntersection(setA2, setB2);
        ArrayList<Float> expectedResult2 = new ArrayList<>();
        printResult("Test set intersection 2", result2.equals(expectedResult2));

        // Test 3: Test with sets having no common elements
        ArrayList<Float> setA3 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB3 = new ArrayList<>(Arrays.asList(4.0f, 5.0f, 6.0f));
        ArrayList<Float> result3 = StatisticsModel.setIntersection(setA3, setB3);
        ArrayList<Float> expectedResult3 = new ArrayList<>();
        printResult("Test set intersection 3", result3.equals(expectedResult3));

        // Test 4: Test with sets having all common elements
        ArrayList<Float> setA4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> result4 = StatisticsModel.setIntersection(setA4, setB4);
        ArrayList<Float> expectedResult4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        printResult("Test set intersection 4", result4.equals(expectedResult4));

        // Test 5: Test with sets having duplicate elements
        ArrayList<Float> setA5 = new ArrayList<>(Arrays.asList(1.0f, 1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB5 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 2.0f, 3.0f));
        ArrayList<Float> result5 = StatisticsModel.setIntersection(setA5, setB5);
        ArrayList<Float> expectedResult5 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        printResult("Test set intersection 5", result5.equals(expectedResult5));

        // Use assertEquals for exact comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testSetUnion() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> setA1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        ArrayList<Float> setB1 = new ArrayList<>(Arrays.asList(4.0f, 5.0f, 6.0f, 7.0f, 8.0f));
        ArrayList<Float> result1 = StatisticsModel.setUnion(setA1, setB1);
        ArrayList<Float> expectedResult1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f));
        printResult("Test set union 1", result1.equals(expectedResult1));

        // Test 2: Test with empty sets
        ArrayList<Float> setA2 = new ArrayList<>();
        ArrayList<Float> setB2 = new ArrayList<>();
        ArrayList<Float> result2 = StatisticsModel.setUnion(setA2, setB2);
        ArrayList<Float> expectedResult2 = new ArrayList<>();
        printResult("Test set union 2", result2.equals(expectedResult2));

        // Test 3: Test with sets having no common elements
        ArrayList<Float> setA3 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB3 = new ArrayList<>(Arrays.asList(4.0f, 5.0f, 6.0f));
        ArrayList<Float> result3 = StatisticsModel.setUnion(setA3, setB3);
        ArrayList<Float> expectedResult3 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f));
        printResult("Test set union 3", result3.equals(expectedResult3));

        // Test 4: Test with sets having all common elements
        ArrayList<Float> setA4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        ArrayList<Float> result4 = StatisticsModel.setUnion(setA4, setB4);
        ArrayList<Float> expectedResult4 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        printResult("Test set union 4", result4.equals(expectedResult4));

        // Test 5: Test with sets having duplicate elements
        ArrayList<Float> setA5 = new ArrayList<>(Arrays.asList(1.0f, 1.0f, 2.0f, 3.0f));
        ArrayList<Float> setB5 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 2.0f, 3.0f));
        ArrayList<Float> result5 = StatisticsModel.setUnion(setA5, setB5);
        ArrayList<Float> expectedResult5 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        printResult("Test set union 5", result5.equals(expectedResult5));

        // Test 6: Test with large sets
        ArrayList<Float> setA6 = generateLargeSet();
        ArrayList<Float> setB6 = generateLargeSet();
        ArrayList<Float> result6 = StatisticsModel.setUnion(setA6, setB6);
        // Ensure that the result set contains all elements from both sets
        boolean assertionResult6 = result6.containsAll(setA6) && result6.containsAll(setB6);
        printResult("Test set union 6", assertionResult6);

        // Use assertEquals for exact comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
        assertTrue(result6.containsAll(setA6) && result6.containsAll(setB6));
    }

    @Test
    public void testCartesianProduct() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> setA1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f));
        ArrayList<Float> setB1 = new ArrayList<>(Arrays.asList(3.0f, 4.0f));
        ArrayList<Pair> result1 = StatisticsModel.cartesianProduct(setA1, setB1);
        ArrayList<Pair> expectedResult1 = new ArrayList<>();
        expectedResult1.add(new Pair(1.0f, 3.0f));
        expectedResult1.add(new Pair(1.0f, 4.0f));
        expectedResult1.add(new Pair(2.0f, 3.0f));
        expectedResult1.add(new Pair(2.0f, 4.0f));

        // Check if the sizes of the two lists are equal
        boolean sizeEquals = expectedResult1.size() == result1.size();
        printResult("Test Cartesian Product Size Equality 1: ", sizeEquals);

        // Compare each element of the lists
        boolean allElementsEqual = true;
        for (int i = 0; i < expectedResult1.size(); i++) {
            boolean elementEquals = expectedResult1.get(i).equals(result1.get(i));
            if (!elementEquals) {
                allElementsEqual = false;
                break;
            }
        }
        printResult("Test Cartesian Product Elements Equality 1: ", allElementsEqual);
    }



    @Test
    public void testCalcRange() {
        System.out.println();
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(19.0f, 21.0f, 23.0f, 20.0f, 23.0f, 27.0f, 25.0f, 24.0f, 31.0f));
        BigDecimal result1 = StatisticsModel.calcRange(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(12); // Adjust this according to your implementation
        printResult("Test calculate range 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calcRange(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(5); // Adjust this according to your implementation
        printResult("Test calculate range 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calcRange(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(261.7); // Adjust this according to your implementation
        printResult("Test calculate range 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calcRange(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0); // Adjust this according to your implementation
        printResult("Test calculate range 4", result4.equals(expectedResult4));

        // Large Number Input Test
        ArrayList<Float> testData5 = bigNumsArray2; // Assuming bigNumsArray2 is defined elsewhere
        BigDecimal result5 = StatisticsModel.calcRange(testData5);
        BigDecimal expectedResult5 = new BigDecimal("400000000"); // Adjust this according to your implementation
        printResult("Test calculate range 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testCalculateMeanAbsoluteDeviation() {
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f, 4.0f, 5.0f));
        BigDecimal result1 = StatisticsModel.calculateMeanAbsoluteDeviation(testData1);
        BigDecimal expectedResult1 = BigDecimal.valueOf(1.2); // Expected mean absolute deviation
        printResult("Test calculate mean absolute deviation 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.0f, -1.0f, 0.0f, 1.0f, 2.0f));
        BigDecimal result2 = StatisticsModel.calculateMeanAbsoluteDeviation(testData2);
        BigDecimal expectedResult2 = BigDecimal.valueOf(1.2); // Expected mean absolute deviation
        printResult("Test calculate mean absolute deviation 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(-10.0f, -5.0f, 0.0f, 5.0f, 10.0f));
        BigDecimal result3 = StatisticsModel.calculateMeanAbsoluteDeviation(testData3);
        BigDecimal expectedResult3 = BigDecimal.valueOf(6.0); // Expected mean absolute deviation
        printResult("Test calculate mean absolute deviation 3", result3.equals(expectedResult3));

        // Test 4: Test where all values are the same
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateMeanAbsoluteDeviation(testData4);
        BigDecimal expectedResult4 = BigDecimal.valueOf(0.0); // Expected mean absolute deviation
        printResult("Test calculate mean absolute deviation 4", result4.equals(expectedResult4));

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
    }

    @Test
    public void testCalculatePopulationCoefficientOfVariation() {
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(19.0f, 21.0f, 23.0f, 20.0f, 23.0f, 27.0f, 25.0f, 24.0f, 31.0f));
        BigDecimal result1 = StatisticsModel.calculateCoefficientOfVariation(testData1, true);
        BigDecimal expectedResult1 = new BigDecimal("0.15"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateCoefficientOfVariation(testData2, true);
        BigDecimal expectedResult2 = new BigDecimal("0"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateCoefficientOfVariation(testData3, true);
        BigDecimal expectedResult3 = new BigDecimal("3.13"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateCoefficientOfVariation(testData4, true);
        BigDecimal expectedResult4 = new BigDecimal("0");
        printResult("Test calculate population coefficient of variation 4", result4.equals(expectedResult4));

        // Test 5: Large number input test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateCoefficientOfVariation(testData5, true);
        BigDecimal expectedResult5 = new BigDecimal("0.64"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }

    @Test
    public void testCalculateSampleCoefficientOfVariation() {
        // Test 1: Simple test
        ArrayList<Float> testData1 = new ArrayList<>(Arrays.asList(19.0f, 21.0f, 23.0f, 20.0f, 23.0f, 27.0f, 25.0f, 24.0f, 31.0f));
        BigDecimal result1 = StatisticsModel.calculateCoefficientOfVariation(testData1, false);
        BigDecimal expectedResult1 = new BigDecimal("0.16"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 1", result1.equals(expectedResult1));

        // Test 2: Test with negative, positive, and zero values
        ArrayList<Float> testData2 = new ArrayList<>(Arrays.asList(-2.5f, -1.0f, 0.0f, 1.0f, 2.5f));
        BigDecimal result2 = StatisticsModel.calculateCoefficientOfVariation(testData2, false);
        BigDecimal expectedResult2 = new BigDecimal("0"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 2", result2.equals(expectedResult2));

        // Test 3: Another test with negative and positive input data
        ArrayList<Float> testData3 = new ArrayList<>(Arrays.asList(
                100.2345f, 30.9845f, -20.0999f, 50.4567f, -80.1234f,
                60.3456f, -70.9876f, 90.8765f, -40.2345f, 20.3456f,
                -10.1234f, 80.5678f, -50.7890f, 120.9876f, -90.8765f,
                110.3456f, -30.4567f, 40.9876f, -60.1234f, 70.5678f,
                -20.3456f, 130.7890f, -80.8765f, 140.3456f, -70.4567f,
                150.9876f, -60.1234f, 160.5678f, -50.3456f, 170.7890f));
        BigDecimal result3 = StatisticsModel.calculateCoefficientOfVariation(testData3, false);
        BigDecimal expectedResult3 = new BigDecimal("3.18"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 3", result3.equals(expectedResult3));

        // Test 4: Test where expected result would be a zero value
        ArrayList<Float> testData4 = new ArrayList<>(Arrays.asList(3.0f, 3.0f, 3.0f, 3.0f, 3.0f));
        BigDecimal result4 = StatisticsModel.calculateCoefficientOfVariation(testData4, false);
        BigDecimal expectedResult4 = new BigDecimal("0");
        printResult("Test calculate population coefficient of variation 4", result4.equals(expectedResult4));

        // Test 5: Large number input test
        ArrayList<Float> testData5 = bigNumsArray;
        BigDecimal result5 = StatisticsModel.calculateCoefficientOfVariation(testData5, false);
        BigDecimal expectedResult5 = new BigDecimal("0.78"); // Adjust this according to your implementation
        printResult("Test calculate population coefficient of variation 5", result5.equals(expectedResult5));

        // Use assertEquals for floating-point comparison
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
        assertEquals(expectedResult3, result3);
        assertEquals(expectedResult4, result4);
        assertEquals(expectedResult5, result5);
    }


    @Test
    public void testMarginOfError() {
        double result = StatisticsModel.calculateMarginOfError(.85, 1, .20);
        double expectedResult = 5.7;
        double delta = 0.01; // Adjust delta based on acceptable margin of error

        assertEquals(expectedResult, result, delta);
    }

    @Test
    public void testCalculateSampleSize() {
        double result = StatisticsModel.calculateSampleSize(.85, .05, .50);

        double expectedResult = 208 ;
        double delta = 0.0;

        double result2 = StatisticsModel.calculateSampleSize(.85, .05, .70);
        double expectedResult2 = 110;

        assertEquals(expectedResult, result, delta);

        assertEquals(expectedResult2, result2, delta);
    }
}