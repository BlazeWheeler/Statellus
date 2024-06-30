package com.blazewheeler.statellus.utils

import java.math.BigDecimal
import java.text.DecimalFormat

object StringUtils {

    /**
     * Converts a floating-point number to a string with commas for thousands separator if it is an integer.
     *
     * @param float The floating-point number to convert.
     * @return The formatted string.
     */


    fun decimalFormatter(solution: String) : String {

            val number = solution.toBigDecimalOrNull() ?: return "Invalid input" // Convert solution to BigDecimal or return error message if invalid

            // Create DecimalFormat with pattern
            val decimalFormatter = DecimalFormat("###")

            // Format the number using the DecimalFormat
            return decimalFormatter.format(number)

    }
    fun zeroDecimalToInt(float: Float): String {
        return if (float.rem(1) == 0f) {
            formatWithCommas(float.toInt())
        } else {
            formatWithCommas(float)
        }
    }

    /**
     * Converts a BigDecimal value to a string with commas for thousands separator.
     *
     * @param value The BigDecimal value to convert.
     * @return The formatted string.
     */

    fun bigZeroDecimalToInt(value: BigDecimal): String {
        val intValue = if (value.stripTrailingZeros().scale() <= 0) {
            value.setScale(0)
        } else {
            value
        }

        return formatWithCommas(intValue)
    }

    /**
     * Formats a number with commas for thousands separator.
     *
     * @param number The number to format.
     * @return The formatted string.
     */
    fun formatWithCommas(number: Number): String {
        val formatter = java.text.DecimalFormat("###,###.###")
        return formatter.format(number)
    }

    /**
     * Adds a solution to a LaTeX string.
     *
     * @param latexString The original LaTeX string.
     * @param solution    The solution to add.
     * @return The updated LaTeX string.
     */
    fun addSolutionToLatex(latexString: String, solution: String): String {
        val stringBuilder = StringBuilder(latexString)
        val meanWithNewLines = StringBuilder(solution)
        for (i in meanWithNewLines.length - 48 downTo 0 step 48) {
            meanWithNewLines.insert(i, "\\\\\n")
        }
        stringBuilder.append(meanWithNewLines)
        return stringBuilder.toString()
    }

    /**
     * Adds a list of BigDecimal modes to a LaTeX string.
     *
     * @param latexString The original LaTeX string.
     * @param modes       The list of BigDecimal modes to add.
     * @return The updated LaTeX string.
     */
    fun addBigModesToLatex(latexString: String, modes: List<BigDecimal>): String {
        val stringBuilder = StringBuilder(latexString)
        if (modes.isNotEmpty()) {
            val modeBuilder = StringBuilder()
            for (mode in modes) {
                val modeString = bigZeroDecimalToInt(mode)
                modeBuilder.append(modeString).append(", ")
            }
            val modeString = modeBuilder.toString().trimEnd { it == ' ' }
            for (i in modeString.indices) {
                if (i > 0 && i % 48 == 0) { // Insert new line after every 20 characters
                    stringBuilder.append("\\\\\n")
                }
                stringBuilder.append(modeString[i])
            }
        } else {
            stringBuilder.append("N/A")
        }
        return removeTrailingComma(stringBuilder.toString())
    }

    /**
     * Removes the trailing comma from a string if present.
     *
     * @param input The input string.
     * @return The string with trailing comma removed if present.
     */
    @JvmStatic
    fun removeTrailingComma(input: String): String {
        // Check if the input string ends with a comma
        if (input.isNotEmpty() && input.last() == ',') {
            // If it does, remove the last character (which is the comma)
            return input.substring(0, input.length - 1)
        }
        // If the input string does not end with a comma, return the input string as it is
        return input
    }


    /**
     * Formats Number Sequence List.
     *
     * @param input The input ArrayList.
     * @return Formatted string.
     */
    fun formatSequenceString(input: ArrayList<BigDecimal>) :String {
        var formattedSequenceString = ""
            formattedSequenceString = input.toString().replace("[\\[\\]]".toRegex(), "")
            formattedSequenceString = "$formattedSequenceString,..."

        return formattedSequenceString
    }
}
