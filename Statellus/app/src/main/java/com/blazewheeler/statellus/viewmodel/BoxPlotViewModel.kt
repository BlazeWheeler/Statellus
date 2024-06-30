package com.blazewheeler.statellus.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

/**
 * ViewModel class for generating LaTeX strings and converting data strings to ArrayLists.
 */
class BoxPlotViewModel : ViewModel()  {

    /**
     * Generates a LaTeX string representing the Five Number Summary table.
     *
     * @param min The minimum value.
     * @param q1 The first quartile value.
     * @param median The median value.
     * @param q3 The third quartile value.
     * @param max The maximum value.
     * @param range The range value.
     * @return The LaTeX string representing the Five Number Summary table.
     */
    fun generateLatexString(
        min: BigDecimal? = null,
        q1: BigDecimal? = null,
        median: Float? = null,
        q3: BigDecimal? = null,
        max: BigDecimal? = null,
        range: BigDecimal? = null
    ): String {
        return """
        \begin{tabular}{|c|c|}
        \hline
        \multicolumn{2}{|c|}{Five Number Summary} \\
        \hline
        MIN & ${min ?: "NA"} \\
        Q1 & ${q1 ?: "NA"} \\
        Median & ${median ?: "NA"} \\
        Q3 & ${q3 ?: "NA"} \\
        MAX & ${max ?: "NA"} \\
        Range & ${range ?: "NA"} \\
        \hline
        \end{tabular}
    """.trimIndent()
    }

    /**
     * Converts a comma-separated data string to an ArrayList of Float values.
     *
     * @param dataString The comma-separated data string.
     * @return An ArrayList of Float values parsed from the data string.
     */
    fun dataStringToArrayList(dataString: String): ArrayList<Float> {
        if (dataString.isEmpty()) {
            Log.d("dataStringTo", "its empty")
            return ArrayList()  // Return an empty list if the input is empty
        }
        val separatedValues = dataString.split(",").map { it.toFloat() }
        return ArrayList(separatedValues)
    }
}
