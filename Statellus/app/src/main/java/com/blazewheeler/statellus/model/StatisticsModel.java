package com.blazewheeler.statellus.model;

import static java.math.BigDecimal.*;

import com.blazewheeler.statellus.utils.Pair;

import org.apache.commons.math3.distribution.NormalDistribution;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class containing various statistical calculations and set operations.
 */
public class StatisticsModel {


    static DecimalFormat df = new DecimalFormat("#.#");
    static DecimalFormat cvDecimalFormatter = new DecimalFormat("#.##");

    // TODO: Optimize functions for big numbers
    // TODO: Create and refactor error handling for float arrays and create float to string method

    public static BigDecimal calcBigMean(ArrayList<Float> data) {
        int numOfValues = data.size();
        BigDecimal total = ZERO;

        // Add all data points
        for (float num : data) {
            total = total.add(valueOf(num));
        }

        // Calculate mean using BigDecimal division
        BigDecimal mean = total.divide(valueOf(numOfValues), 1, RoundingMode.HALF_UP);

        return new BigDecimal(df.format(mean));
    }

    /**
     * Calculates the median of a list of numbers using BigDecimal.
     *
     * @param data The list of numbers
     * @return The median value as a BigDecimal
     */
    public static BigDecimal calculateBigMedian(ArrayList<Float> data) {
        Collections.sort(data);
        int size = data.size();

        // Even Sample Size
        if (size % 2 == 0) {
            BigDecimal middleElement1 = valueOf(data.get(size / 2 - 1));
            BigDecimal middleElement2 = valueOf(data.get(size / 2));
            BigDecimal sum = middleElement1.add(middleElement2);
            BigDecimal result = sum.divide(valueOf(2), 1, RoundingMode.HALF_UP);

            return new BigDecimal(df.format(result));
        }
        // Odd Sample Size
        else {
            return new BigDecimal(df.format(data.get(size / 2)));
        }
    }

    /**
     * Calculates the median of a list of numbers.
     *
     * @param data The list of numbers
     * @return The median value
     */
    public static Float calculateMedian(ArrayList<Float> data) {
        float median;
        Collections.sort(data);
        int size = data.size();

        // Even Sample Size
        if (data.size() % 2 == 0) {
            float middleElement1 = data.get(size / 2 - 1);
            float middleElement2 = data.get(size / 2);
            median = (middleElement1 + middleElement2) / 2;
            // Odd Sample Size
        } else {
            median = data.get(size / 2);
        }
        return median;
    }


    /**
     * Calculates the mode of a list of numbers using BigDecimal.
     *
     * @param data The list of numbers
     * @return The mode values as a list of BigDecimal
     */
    public static List<BigDecimal> calculateBigMode(ArrayList<Float> data) {
        // Create a HashMap to store the frequency of each element
        HashMap<Float, Integer> frequencyMap = new HashMap<>();

        // Count the occurrences of each element
        for (Float num : data) {
            Integer currentFrequency = frequencyMap.get(num);
            int newFrequency = (currentFrequency != null) ? currentFrequency + 1 : 1;
            frequencyMap.put(num, newFrequency);
        }

        // Find the maximum frequency
        int maxFrequency = Collections.max(frequencyMap.values());

        // Find all elements with the maximum frequency (modes)
        List<BigDecimal> modes = new ArrayList<>();
        for (Float key : frequencyMap.keySet()) {
            Integer frequency = frequencyMap.get(key);
            if (frequency != null && frequency == maxFrequency) {
                BigDecimal mode = BigDecimal.valueOf(key);
                modes.add(new BigDecimal(df.format(mode)));
            }
        }

        // Sort modes in ascending order
        Collections.sort(modes);

        return modes;
    }

    /**
     * Calculates the variance of a list of numbers using BigDecimal.
     *
     * @param data         The list of numbers
     * @param isPopulation Flag indicating if the data represents a population
     * @return The variance value as a BigDecimal
     */
    public static BigDecimal calculateVariance(ArrayList<Float> data, Boolean isPopulation) {
        // Calculate the mean
        BigDecimal mean = calcBigMean(data);

        // Calculate the sum of squared differences from the mean
        BigDecimal sumSquaredDiff = ZERO;
        BigDecimal bdSize = valueOf(data.size());
        BigDecimal sampleVariance;

        for (Float num : data) {
            BigDecimal bdNum = valueOf(num);
            BigDecimal diff = bdNum.subtract(mean);
            sumSquaredDiff = sumSquaredDiff.add(diff.pow(2));
        }

        if (!isPopulation) {
            // Calculate the sample variance (divide by n - 1)
            sampleVariance = sumSquaredDiff.divide(bdSize.subtract(ONE), 1, RoundingMode.HALF_UP);
        } else {
            // Calculate the sample variance (divide by n )
            sampleVariance = sumSquaredDiff.divide(bdSize, 1, RoundingMode.HALF_UP);
        }

        return new BigDecimal(df.format(sampleVariance));
    }

    /**
     * Calculates the standard deviation of a list of numbers using BigDecimal.
     *
     * @param data         The list of numbers
     * @param isPopulation Flag indicating if the data represents a population
     * @return The standard deviation value as a BigDecimal
     */
    public static BigDecimal calculateStandardDeviation(ArrayList<Float> data, Boolean isPopulation) {
        // Calculate the mean
        BigDecimal mean = calcBigMean(data);

        // Calculate the sum of squared differences from the mean
        BigDecimal sumSquaredDiff = ZERO;
        BigDecimal nMinusOne = valueOf(data.size()).subtract(ONE);
        BigDecimal sampleVariance;

        for (Float num : data) {
            BigDecimal bdNum = valueOf(num);
            BigDecimal diff = bdNum.subtract(mean);
            sumSquaredDiff = sumSquaredDiff.add(diff.multiply(diff));
        }

        // Calculate the sample variance

        if (!isPopulation) {
            sampleVariance = sumSquaredDiff.divide(nMinusOne, MathContext.DECIMAL128);
        } else {
            sampleVariance = sumSquaredDiff.divide(valueOf(data.size()), MathContext.DECIMAL128);
        }


        // Calculate the sample standard deviation
        BigDecimal sampleStdDev = BigDecimalSquareRoot(sampleVariance, MathContext.DECIMAL128);

        return new BigDecimal(df.format(sampleStdDev));
    }

    /**
     * Calculates the square root of a BigDecimal value using Newton's method.
     *
     * @param value   The BigDecimal value
     * @param context The MathContext for precision
     * @return The square root as a BigDecimal
     */
    public static BigDecimal BigDecimalSquareRoot(BigDecimal value, MathContext context) {
        // Check if value is negative
        if (value.compareTo(ZERO) <= 0) {
            return ZERO; // Return zero for negative input
        }

        // Initial guess for the square root (value / 2)
        BigDecimal guess = value.divide(valueOf(2), context);

        // Perform iterations of Newton's method until convergence
        final BigDecimal TWO = valueOf(2);
        final BigDecimal PRECISION = ONE.scaleByPowerOfTen(-context.getPrecision());

        while (true) {
            // Calculate new guess using Newton's method
            BigDecimal newGuess = value.divide(guess, context);
            newGuess = newGuess.add(guess);
            newGuess = newGuess.divide(TWO, context);

            // Check for convergence
            if (newGuess.subtract(guess).abs().compareTo(PRECISION) < 0) {
                break; // Convergence achieved
            }
            guess = newGuess; // Update guess for next iteration
        }
        return guess;
    }

    /**
     * Calculates the maximum value in a list of numbers.
     *
     * @param data The list of numbers
     * @return The maximum value
     */
    public static BigDecimal calculateMax(ArrayList<Float> data) {
        BigDecimal max = valueOf(data.get(0));

        for (float num : data) {
            if (valueOf(num).compareTo(max) > 0) {
                max = valueOf(num);
            }
        }
        return new BigDecimal(df.format(max));
    }

    /**
     * Calculates the minimum value in a list of numbers.
     *
     * @param data The list of numbers
     * @return The minimum value
     */
    public static BigDecimal calculateMin(ArrayList<Float> data) {
        BigDecimal min = valueOf(data.get(0));

        for (float num : data) {
            if (valueOf(num).compareTo(min) < 0) {
                min = valueOf(num);
            }
        }
        return new BigDecimal(df.format(min));
    }


    /**
     * Calculates the first quartile of a list of numbers.
     *
     * @param data The list of numbers
     * @return The first quartile
     */
    public static BigDecimal calculateInnerQuartile(ArrayList<Float> data) {
        Collections.sort(data);
        BigDecimal q1;
        int size = data.size();

        if (size % 2 == 0) {
            // For even-sized list, take the median of the lower half
            int middleIndex = size / 2;
            ArrayList<Float> dataSublist = new ArrayList<>(data.subList(0, middleIndex));
            q1 = calculateBigMedian(dataSublist);
            // Odd Sample Size
        } else {
            // For odd-sized list, take the median of the data points from index 0 to (n/2)-1
            int middleIndex = (size - 1) / 2;
            ArrayList<Float> dataSublist = new ArrayList<>(data.subList(0, middleIndex));
            q1 = calculateBigMedian(dataSublist);
        }
        return new BigDecimal(df.format(q1));
    }


    /**
     * Calculates the third quartile of a list of numbers.
     *
     * @param data The list of numbers
     * @return The third quartile
     */
    public static BigDecimal calculateThirdQuartile(ArrayList<Float> data) {
        // Sort the data in ascending order
        Collections.sort(data);

        BigDecimal q3;
        int size = data.size();

        int middleIndex = size / 2;
        ArrayList<Float> upperHalf;
        if (size % 2 == 0) {
            // For even-sized list, take the median of the upper half
            upperHalf = new ArrayList<>(data.subList(middleIndex, size));
        } else {
            // For odd-sized list, take the median of the data points from index (n/2)+1 to n
            upperHalf = new ArrayList<>(data.subList(middleIndex + 1, size));
        }
        q3 = calculateBigMedian(upperHalf);
        return new BigDecimal(df.format(q3));
    }


    /**
     * Calculates the inter quartile range of a list of numbers.
     *
     * @param data The list of numbers
     * @return The inter quartile range as a BigDecimal
     */
    public static BigDecimal calcInterQuartileRange(ArrayList<Float> data) {

        BigDecimal q1, q3, iqr;
        q3 = calculateThirdQuartile(data);
        q1 = calculateInnerQuartile(data);
        iqr = q3.subtract(q1);

        return new BigDecimal(df.format(iqr));
    }

    /**
     * Calculates the union of two sets.
     *
     * @param a The first set
     * @param b The second set
     * @return The union of the two sets
     */
    public static ArrayList<Float> setUnion(ArrayList<Float> a, ArrayList<Float> b) {
        ArrayList<Float> aUnionB = new ArrayList<>();
        // Combine Lists
        aUnionB.addAll(b);
        aUnionB.addAll(a);

        // Remove Duplicates
        Set<Float> set = new HashSet<>(aUnionB);
        aUnionB.clear();
        aUnionB.addAll(set);

        // Sort List
        aUnionB.sort(Comparator.naturalOrder());

        return aUnionB;
    }

    /**
     * Calculates the intersection of two sets.
     *
     * @param a The first set
     * @param b The second set
     * @return The intersection of the two sets
     */
    public static ArrayList<Float> setIntersection(ArrayList<Float> a, ArrayList<Float> b) {
        ArrayList<Float> aIntersectionB = new ArrayList<>();

        // Check for common elements between sets
        for (Float num : a) {
            if (b.contains(num)) {
                aIntersectionB.add(num);
            }
        }

        Set<Float> set = new HashSet<>(aIntersectionB);

        aIntersectionB.clear();
        aIntersectionB.addAll(set);
        aIntersectionB.sort(Comparator.naturalOrder());

        return aIntersectionB;
    }


    /**
     * Calculates the Cartesian product of two ArrayLists of Float values.
     *
     * @param a The first ArrayList
     * @param b The second ArrayList
     * @return The Cartesian product as an ArrayList of Pair objects
     */
    public static ArrayList<Pair> cartesianProduct(ArrayList<Float> a, ArrayList<Float> b) {
        // Initialize the ArrayList to store the Cartesian product pairs
        ArrayList<Pair> cartesianProduct = new ArrayList<>();

        for (Float elementA : a) {
            // Iterate through each element in the second ArrayList
            for (Float elementB : b) {
                // Create a Pair object with the current elements and add it to the Cartesian product list
                cartesianProduct.add(new Pair(elementA, elementB));
            }
        }
        return cartesianProduct;
    }


    /**
     * Calculates the range of a list of numbers.
     *
     * @param data The list of numbers
     * @return The range as a BigDecimal
     */
    public static BigDecimal calcRange(ArrayList<Float> data) {
        data.sort(Comparator.naturalOrder());
        BigDecimal range, smallest, largest;
        largest = valueOf(data.get(data.size() - 1));
        smallest = valueOf(data.get(0));
        range = largest.subtract(smallest);

        return new BigDecimal(df.format(range));
    }

    /**
     * Calculates the mean absolute deviation of a list of numbers.
     *
     * @param data The list of numbers
     * @return The mean absolute deviation as a BigDecimal
     */
    public static BigDecimal calculateMeanAbsoluteDeviation(ArrayList<Float> data) {
        // Calculate the mean
        BigDecimal mean = calcBigMean(data);

        // Calculate the sum of absolute differences from the mean

        BigDecimal sumAbsoluteDiff = ZERO;
        for (float num : data) {

            sumAbsoluteDiff = sumAbsoluteDiff.add(valueOf(Math.abs(num - mean.floatValue())));
        }

        // Calculate the mean absolute deviation
        BigDecimal meanAbsoluteDeviation;
        meanAbsoluteDeviation = sumAbsoluteDiff.divide(BigDecimal.valueOf(data.size()), RoundingMode.HALF_UP);

        return meanAbsoluteDeviation;
    }


    /**
     * Calculates the coefficient of variation (CV) of a list of numbers.
     *
     * @param data         The list of numbers
     * @param isPopulation Whether the data represents a population (true) or a sample (false)
     * @return The coefficient of variation as a BigDecimal
     */
    public static BigDecimal calculateCoefficientOfVariation(ArrayList<Float> data, boolean isPopulation) {
        // Calculate the sample standard deviation
        BigDecimal stdDev;

        if (isPopulation) {
            stdDev = calculateStandardDeviation(data, true);
        } else {
            stdDev = calculateStandardDeviation(data, false);
        }

        // Calculate the mean
        BigDecimal mean = calcBigMean(data);

        // Check if the mean is zero to avoid divide by zero error
        if (mean.compareTo(ZERO) == 0) {
            // Return zero if the mean is zero
            return ZERO;
        }

        // Calculate the coefficient of variation (CV)
        BigDecimal cv = stdDev.divide(mean, 2, RoundingMode.HALF_UP);
        //noinspection ResultOfMethodCallIgnored
        valueOf(100).multiply(cv);

        return new BigDecimal(cvDecimalFormatter.format(cv));
    }

    /**
     * Calculates the margin of error for a given confidence level, sample size, and population proportion.
     *
     * @param confidenceLevel      The desired confidence level (e.g., 95% confidence level would be 0.95).
     * @param sampleSize           The size of the sample.
     * @param populationProportion The estimated proportion of the population.
     * @return The margin of error as a percentage.
     */
    public static double calculateMarginOfError(double confidenceLevel, double sampleSize, double populationProportion) {
        // Calculate the z-score corresponding to the confidence level
        double zScore = getZScore(confidenceLevel);

        // Calculate the standard error using the formula: SE = sqrt((p * (1 - p)) / n)
        double standardError = Math.sqrt((populationProportion * (1 - populationProportion)) / sampleSize);

        // Calculate the margin of error using the formula: MOE = z * SE
        double marginOfError = zScore * standardError;

        // Move the decimal point to the right by one position
        marginOfError /= 10;

        final double v = Double.parseDouble(String.format("%.1f", marginOfError * 100));
        return v;
    }

    /**
     * Calculates the sample size required for a given confidence level, margin of error, and population proportion.
     *
     * @param confidenceLevel      The desired confidence level (e.g., 95% confidence level would be 0.95).
     * @param marginOfError        The desired margin of error as a percentage.
     * @param populationProportion The estimated proportion of the population.
     * @return The required sample size.
     */
    public static double calculateSampleSize(double confidenceLevel, double marginOfError, double populationProportion) {
        double zScore = getZScore(confidenceLevel);
        double p = populationProportion;
        double q = 1 - p;

        // Calculate the sample size formula: n = (z^2 * p * q) / (E^2)
        double sampleSize = (Math.pow(zScore, 2) * p * q) / Math.pow(marginOfError, 2);

        // Round up to the next whole number
        sampleSize = Math.ceil(sampleSize);

        return sampleSize;
    }

    /**
     * Calculate the Z-score for a given confidence level.
     *
     * @param confidenceLevel The desired confidence level.
     * @return The Z-score corresponding to the confidence level.
     */
    private static double getZScore(double confidenceLevel) {
        // Convert confidence level to standard normal distribution Z-score
        return new NormalDistribution().inverseCumulativeProbability((1 + confidenceLevel) / 2);
    }
}