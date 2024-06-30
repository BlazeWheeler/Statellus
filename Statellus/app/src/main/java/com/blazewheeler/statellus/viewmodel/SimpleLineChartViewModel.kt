package com.blazewheeler.statellus.viewmodel

import com.blazewheeler.statellus.utils.CustomLineChartRenderer
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

/**
 * ViewModel class for processing data and handling logic related to the SimpleLineChartActivity.
 */
class SimpleLineChartViewModel : ViewModel() {

    /**
     * Processes the input data from the EditText and updates the LineChart accordingly.
     *
     * @param inputData The EditText containing the input data.
     * @param lineChart The LineChart to be updated.
     */
    fun processData(inputData: EditText, lineChart: LineChart) {
        val dataString = inputData.text.toString()
        val entries = ArrayList<Entry>()
        try {
            if (TextUtils.isEmpty(dataString)) {
                inputData.error = "Enter Values."
            } else {
                val separatedValues = ArrayList(dataString.split(",").dropLastWhile { it.isEmpty() })
                for (i in separatedValues.indices) {
                    val value = separatedValues[i].toFloat()
                    // Adjust the index of the entry to create space before the first point
                    entries.add(Entry((i + 1).toFloat(), value))
                }
                if (entries.size <= 1) {
                    inputData.error = "Please enter a data set with at least two elements."
                } else if (entries.size > 15) {
                    lineChart.clear()
                    inputData.error = "16 elements may create a cluttered chart. Try using a bar chart for bigger data sets. "
                } else {
                    val dataSet = LineDataSet(entries, "Data")

                    // Set circle color to black
                    dataSet.circleHoleColor = Color.BLACK

                    // Set other properties of the dataset
                    dataSet.setDrawValues(false)
                    dataSet.circleHoleRadius = 7f
                    dataSet.lineWidth = 5f
                    dataSet.circleRadius = 6f

                    // Set the dataset to LineData
                    val lineData = LineData(dataSet)

                    // Set LineData to LineChart
                    lineChart.data = lineData

                    // Remove bottom X-axis
                    lineChart.xAxis.isEnabled = false

                    // Add CustomLineChartRenderer
                    lineChart.renderer = CustomLineChartRenderer(lineChart, lineChart.animator, lineChart.viewPortHandler)

                    // Customize chart appearance
                    lineChart.axisRight.isEnabled = false
                    lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

                    lineChart.axisLeft.isEnabled = true // Enable left Y-axis
                    lineChart.axisLeft.setDrawGridLines(true) // Hide grid lines on Y-axis
                    lineChart.description.isEnabled = false
                    lineChart.legend.isEnabled = false
                    lineChart.setNoDataTextColor(Color.TRANSPARENT)
                    lineChart.setNoDataText("") // Set an empty string for no data text
                    lineChart.setTouchEnabled(false)

                    // Refresh chart
                    lineChart.invalidate()
                    lineChart.visibility = View.VISIBLE
                }
            }
        } catch (e: NumberFormatException) {
            inputData.error = "Enter only comma-separated numeric values."
        }
    }

    /**
     * Resets the input data EditText and clears the LineChart.
     *
     * @param editText The EditText containing the input data.
     * @param lineChart The LineChart to be cleared.
     */
    fun resetData(editText: EditText, lineChart: LineChart) {
        editText.text.clear()
        lineChart.clear()
    }
}
