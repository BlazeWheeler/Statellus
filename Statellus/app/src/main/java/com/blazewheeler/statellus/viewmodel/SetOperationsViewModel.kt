package com.blazewheeler.statellus.viewmodel

import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.model.StatisticsModel.cartesianProduct

class SetOperationsViewModel: ViewModel() {

    /**
     * Formats the input string representing a set for display.
     * @param input The input string representing a set.
     * @return The formatted string representing the set.
     */
    fun formatSetView(input: String): String {
        return if (input.isEmpty()) "∅" else input.replace("[", "").replace("]", "")
    }

    /**
     * Converts the input string representing a set to a list of floats.
     * @param input The input string representing a set.
     * @return The list of floats extracted from the input string.
     */
    fun convertInputToFloatList(input: String): ArrayList<Float> {
        val values = input.split(",").mapNotNull { it.toFloatOrNull() }

        return ArrayList(values)
    }

    /**
     * Checks if the input string is a valid representation of a set.
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    fun isValidInput(input: String): Boolean {
        if (input.isEmpty()){
            return true
        }
        // Split the input by commas
        val values = input.split(",")

        // Check if each value can be converted to a Float
        for (value in values) {
            try {
                value.toFloat()
            } catch (e: NumberFormatException) {
                return false // If conversion fails, input is invalid
            }
        }

        return true // If all values can be converted to Float, input is valid
    }

    /**
     * Formats the solution list to a string representation.
     * @param solutionList The list of floats representing the solution.
     * @return The formatted string representing the solution.
     */
    // TODO: Remove Trailing Zeros
    fun formatSolution(solutionList: List<Float>): String {
        return if (solutionList.isEmpty()) "∅" else solutionList.toString().replace("[", "").replace("]", "")
    }

    fun formatCartesianProduct(a: ArrayList<Float>, b: ArrayList<Float>): String {
        // Calculate the Cartesian product
        val cartesianProduct = cartesianProduct(a, b)

        // Convert Pair objects to string representation and join them with commas
        val formattedProduct = cartesianProduct.joinToString(separator = ", ") { "(${it.first}, ${it.second})" }

        // Return the formatted Cartesian product
        return if (cartesianProduct.isEmpty()) "∅" else formattedProduct
    }




}