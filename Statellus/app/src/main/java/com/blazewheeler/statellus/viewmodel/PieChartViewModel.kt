package com.blazewheeler.statellus.viewmodel

import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.utils.CustomPieChartRenderer
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.NumberFormat

/**
 * ViewModel class for handling data processing and UI updates for a PieChart.
 */
class PieChartViewModel : ViewModel() {

    /**
     * Handles input validation and processing for the provided data, updating UI elements accordingly.
     *
     * @param inputData The EditText for user input data.
     * @param pieChart The PieChart instance to display the data.
     */
    fun processData(inputData: EditText, pieChart: PieChart) {
        val dataString = inputData.text.toString()
        val entries = ArrayList<PieEntry>()
        try {
            if (TextUtils.isEmpty(dataString)) {
                inputData.error = "Enter Values."
            } else {
                val separatedValues = ArrayList(dataString.split(",").dropLastWhile { it.isEmpty() })
                var totalValue = 0f
                for (i in separatedValues.indices) {
                    val value = separatedValues[i].toFloat()
                    totalValue += value
                    entries.add(PieEntry(value, ""))
                }
                if (entries.size <= 1) {
                    inputData.error = "Please enter a data set with at least two elements."
                    entries.clear()
                }
                if (entries.size > 7) {
                    pieChart.clear()
                    inputData.error = "8 elements may create a cluttered chart. Try using a bar chart for bigger data sets. "
                    entries.clear()
                    pieChart.invalidate()
                } else {
                    for (entry in entries) {
                        val percentage = entry.value / totalValue * 100
                        entry.label = entry.label + " (" + String.format("%.1f", percentage) + "%)"
                    }
                    val dataSet = PieDataSet(entries, "")

                    // Set your custom renderer to the PieChart
                    val customRenderer = CustomPieChartRenderer(pieChart)
                    pieChart.renderer = customRenderer
                    // Customizations
                    dataSet.setDrawValues(true)
                    dataSet.valueTextSize = 16f
                    dataSet.valueTypeface = Typeface.DEFAULT_BOLD
                    dataSet.valueTextColor = Color.WHITE
                    dataSet.valueFormatter = object : ValueFormatter() {
                        private val formatter = NumberFormat.getPercentInstance()

                        override fun getFormattedValue(value: Float) =
                            formatter.format(value / 100f)
                    }
                    // Setting custom colors
                    dataSet.colors = getCustomColors()
                    val pieData = PieData(dataSet)
                    pieChart.data = pieData
                    // Hole
                    pieChart.isDrawHoleEnabled = false
                    // Disable legend & description
                    pieChart.legend.isEnabled = false
                    pieChart.description = null
                    pieChart.setTouchEnabled(true)
                    pieChart.invalidate()
                }
            }
        } catch (e: NumberFormatException) {
            inputData.error = "Enter only comma-separated numeric values."
            entries.clear()
        }
    }

    /**
     * Retrieves a list of custom colors for the PieChart data.
     *
     * @return List of custom colors.
     */
    private fun getCustomColors(): List<Int> {
        val colors = mutableListOf<Int>()

        colors.addAll(
            listOf(
                Color.parseColor("#0B40C5"), Color.parseColor("#EC3CAB"),
                Color.parseColor("#ec2f4b"), Color.parseColor("#009FFF"),
                Color.parseColor("#2ebf91"), Color.parseColor("#8360c3"),
                Color.parseColor("#ffdde1"), Color.parseColor("#ee9ca7"),
                Color.parseColor("#DECBA4"), Color.parseColor("#3E5151"),
                Color.parseColor("#12c2e9"), Color.parseColor("#f64f59"),
                Color.parseColor("#eef2f3"), Color.parseColor("#8e9eab")
            )
        )
        return colors
    }

    /**
     * Resets the data and UI elements associated with the PieChart.
     *
     * @param inputData The EditText for user input data.
     * @param pieChart The PieChart instance to reset.
     */
    fun resetData(inputData: EditText, pieChart: PieChart) {
        inputData.text.clear()
        pieChart.clear()
        pieChart.invalidate()
    }
}
