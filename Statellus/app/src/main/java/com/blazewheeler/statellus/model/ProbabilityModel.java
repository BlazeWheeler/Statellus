package com.blazewheeler.statellus.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ProbabilityModel {


    /**
     * Calculates the probability of event A given event B using Bayes' theorem.
     *
     * @param pA       The prior probability of event A.
     * @param pBGivenA The probability of event B given event A.
     * @param pB       The prior probability of event B.
     * @return The probability of event A given event B, rounded to one decimal place.
     */
    public static BigDecimal calculateBayesianProbabilityPbGivenA(BigDecimal pA, BigDecimal pBGivenA, BigDecimal pB) {
        // Calculate the probability of event A given event B using Bayes' theorem
        BigDecimal result = pA.multiply(pBGivenA).divide(pB, MathContext.DECIMAL128);
        return result.setScale(1, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the probability of event B given event A using Bayes' theorem.
     *
     * @param pA       The prior probability of event A.
     * @param pAGivenB The probability of event A given event B.
     * @param pB       The prior probability of event B.
     * @return The probability of event A given event B, rounded to one decimal place.
     */
    public static BigDecimal calculateBayesianProbabilityPaGivenB(BigDecimal pA, BigDecimal pAGivenB, BigDecimal pB) {
        // Calculate the probability of event A given event B using Bayes' theorem
        BigDecimal result = pB.multiply(pAGivenB).divide(pA, MathContext.DECIMAL128);
        return result.setScale(1, RoundingMode.HALF_UP);
    }

    /**
     * Calculates the probability A using Bayes' theorem.
     *
     * @param pB       The prior probability of event B.
     * @param pAGivenB The probability of event A given event B.
     * @param pBGivenA The prior probability of event B given event BG.
     * @return The probability of event A, rounded to one decimal place.
     */
    public static BigDecimal calculateBayesianProbabilityPa(BigDecimal pB, BigDecimal pAGivenB, BigDecimal pBGivenA) {
        // Calculate the probability of event A given event B using Bayes' theorem
        BigDecimal result = pB.multiply(pAGivenB).divide(pBGivenA, MathContext.DECIMAL128);
        return result.setScale(1, RoundingMode.HALF_UP);
    }

}
