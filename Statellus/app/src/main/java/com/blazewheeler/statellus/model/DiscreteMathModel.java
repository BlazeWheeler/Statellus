package com.blazewheeler.statellus.model;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.special.Erf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class containing mathematical calculations for discrete mathematics and statistics.
 */

public class DiscreteMathModel {

    /**
     * Calculates the nth Fibonacci number.
     *
     * @param n The index of the Fibonacci number to calculate.
     * @return The nth Fibonacci number.
     */
static DecimalFormat df = new DecimalFormat("###.#");

    public static BigInteger calculateFibonacci(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        if (n == 0) {
            return a;
        } else if (n == 1) {
            return b;
        }

        for (int i = 2; i <= n; i++) {
            BigInteger temp = b;
            b = b.add(a);
            a = temp;
        }

        return b;
    }

    /**
     * Calculates a sequence of Fibonacci numbers.
     *
     * @param n1 The starting index of the sequence.
     * @param n2 The ending index of the sequence.
     * @return An ArrayList containing the Fibonacci sequence.
     */
    public static ArrayList<BigInteger> calculateFibonacciSequence(int n1, int n2) {

        ArrayList<BigInteger> fibonacciSequence = new ArrayList<>();
        for(int i = n1; i <= n2; i++) {
            fibonacciSequence.add(calculateFibonacci(i));
        }
        return fibonacciSequence;
    }

    /**
     * Calculates the nth term of an arithmetic sequence.
     *
     * @param n The index of the term to calculate.
     * @param a1 The first term of the sequence.
     * @param d The common difference of the sequence.
     * @return The nth term of the arithmetic sequence.
     */
    public static BigDecimal calculateNthTermArithmetic(int n, BigDecimal a1, BigDecimal d) {
        // Formula to calculate the nth term of an arithmetic sequence: a_n = a_1 + (n - 1) * d
        BigDecimal nMinusOne = BigDecimal.valueOf(n - 1);
        return a1.add(d.multiply(nMinusOne));
    }

    /**
     * Calculates the sum of terms in an arithmetic sequence.
     *
     * @param n The number of terms in the sequence.
     * @param a1 The first term of the sequence.
     * @param d The common difference of the sequence.
     * @return The sum of terms in the arithmetic sequence.
     */

    public static BigDecimal calculateSumOfTerms(int n, BigDecimal a1, BigDecimal d) {
        ArrayList<BigDecimal> sequence = calculateArithmeticProgression(n, a1, d);

        BigDecimal solution = BigDecimal.ZERO;

        for(int i = 0; i < sequence.size(); i++) {
            solution = solution.add(sequence.get(i)); // Accumulate sum
        }

        return new BigDecimal(df.format(solution));
    }

   /* public static BigDecimal calculateSumOfTerms(int n, BigDecimal a1, BigDecimal d) {
        // Calculate Sn: Sn = n/2 * [2*a1 + (n - 1)*d]
        BigDecimal nDividedBy2 = BigDecimal.valueOf(n).divide(BigDecimal.valueOf(2), BigDecimal.ROUND_HALF_UP);
        BigDecimal twoTimesA1 = BigDecimal.valueOf(2).multiply(a1);
        BigDecimal nMinusOne = BigDecimal.valueOf(n - 1);
        BigDecimal nMinusOneTimesD = nMinusOne.multiply(d);
        BigDecimal solution = nDividedBy2.multiply(twoTimesA1.add(nMinusOneTimesD));
        return new BigDecimal(df.format(solution)) ;
    }
    *
    */

    /**
     * Calculates the terms of an arithmetic progression.
     *
     * @param n The number of terms to calculate.
     * @param a1 The first term of the progression.
     * @param d The common difference of the progression.
     * @return An ArrayList containing the terms of the arithmetic progression.
     */
    public static ArrayList<BigDecimal> calculateArithmeticProgression(int n, BigDecimal a1, BigDecimal d) {

        ArrayList<BigDecimal> solution = new ArrayList<>();
        BigDecimal curr_term = a1;

        if (n < 10) {
            for (int i = 0; i < n; i++) {
                solution.add(curr_term);
                curr_term = curr_term.add(d);
            }

        } else {
            for (int i = 0; i < 10; i++) {
                solution.add(curr_term);
                curr_term = curr_term.add(d);
            }
        }
        return solution;
    }


    /**
     * Calculates the terms of a geometric sequence.
     *
     * @param firstNumber The first term of the sequence.
     * @param commonRatio The common ratio of the sequence.
     * @param n The number of terms to calculate.
     * @return An ArrayList containing the terms of the geometric sequence.
     */
    public static ArrayList<BigDecimal> calculateGeometricSequence(BigDecimal firstNumber, BigDecimal commonRatio, int n) {
        ArrayList<BigDecimal> sequence = new ArrayList<>();
        BigDecimal term = firstNumber;

        if (n < 10) {
            // Only Output the First 10 Terms
            // In activity: First 10 terms ... nth term
            for (int i = 0; i < n; i++) {
                sequence.add(term);
                term = term.multiply(commonRatio);
            }
        } else {
            // Only Output the First 10 Terms
            // In activity: First 10 terms ... nth term
            for (int i = 0; i < 10; i++) {
                sequence.add(term);
                term = term.multiply(commonRatio);
            }
        }
        return sequence;
    }

    /**
     * Calculates the nth term of a geometric sequence.
     *
     * @param firstNumber The first term of the sequence.
     * @param commonRatio The common ratio of the sequence.
     * @param n The index of the term to calculate.
     * @return The nth term of the geometric sequence.
     */
    public static BigDecimal calculateNthTermOfGeometricSequence(BigDecimal firstNumber, BigDecimal commonRatio, int n) {
        // Calculate the nth term of the geometric sequence: a * r^(n-1)
        BigDecimal nthTerm = firstNumber.multiply(commonRatio.pow(n - 1));
        return nthTerm;
    }

    /**
     * Calculates the geometric sum of terms in a sequence.
     *
     * @param a1 The first term of the sequence.
     * @param r The common ratio of the sequence.
     * @param n The number of terms in the sequence.
     * @return The geometric sum of terms in the sequence.
     */
    public static BigDecimal calculateGeometricSumOfTerms(BigDecimal a1, BigDecimal r, int n) {
        // Calculate Sn: Sn = a1 * (1 - r^n) / (1 - r)
        BigDecimal rToN = r.pow(n);
        BigDecimal numerator = a1.multiply(BigDecimal.ONE.subtract(rToN));
        BigDecimal denominator = BigDecimal.ONE.subtract(r);
        BigDecimal solution = numerator.divide(denominator);
        return new BigDecimal(df.format(solution));
    }

    /**
     * Calculates the Z-score.
     *
     * @param x The value.
     * @param mean The mean of the distribution.
     * @param standardDeviation The standard deviation of the distribution.
     * @return The Z-score.
     */
    public static BigDecimal calculateZScore(BigDecimal x, BigDecimal mean, BigDecimal standardDeviation) {
        // Z = (x - mean) / standardDeviation
        BigDecimal numerator = x.subtract(mean);
        BigDecimal zScore = numerator.divide(standardDeviation, MathContext.DECIMAL128);
        return new BigDecimal(df.format(zScore));
    }

    /**
     * Calculates the cumulative normal distribution function.
     *
     * @param aZValue The Z-score value.
     * @return The P-value.
     */
    public static double calculatePValue(double aZValue) {
        aZValue = aZValue / Math.sqrt(2.0);
        double lPvalue = 0.0;
            lPvalue = Erf.erf(aZValue);

        return lPvalue;
    }

    /**
     * Calculates the probabilities of different scenarios based on a Z-score.
     *
     * @param rawScore The raw score.
     * @param populationMean The mean of the population.
     * @param standardDeviation The standard deviation of the population.
     * @return An ArrayList containing the probabilities.
     */
    public static ArrayList<Double> calculateProbabilities(double rawScore, double populationMean, double standardDeviation) {
        ArrayList<Double> probabilities = new ArrayList<>();

        // Calculate the z-score
        double zScore = (rawScore - populationMean) / standardDeviation;

        // Create a NormalDistribution object
        NormalDistribution normalDistribution = new NormalDistribution();

        // Calculate the probabilities
        double pLessThanZ = normalDistribution.cumulativeProbability(zScore);
        double pBetweenZeroAndZ = normalDistribution.cumulativeProbability(zScore) - normalDistribution.cumulativeProbability(0);

        // Round the probabilities to 5 decimal places after taking absolute values
        BigDecimal roundedPLessThanZ = BigDecimal.valueOf(Math.abs(pLessThanZ)).setScale(5, RoundingMode.HALF_UP);
        BigDecimal roundedPBetweenZeroAndZ = BigDecimal.valueOf(Math.abs(pBetweenZeroAndZ)).setScale(5, RoundingMode.HALF_UP);

        // Add the rounded probabilities to the ArrayList
        probabilities.add(zScore);
        probabilities.add(roundedPLessThanZ.doubleValue());
        probabilities.add(roundedPBetweenZeroAndZ.doubleValue());

        return probabilities;
    }
}
