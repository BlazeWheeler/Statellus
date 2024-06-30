package com.blazewheeler.statellus.view



import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.blazewheeler.statellus.R

import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.viewmodel.PieChartViewModel
import com.github.mikephil.charting.charts.PieChart

class PieChartActivity : AppCompatActivity() {
    private var pieChartViewModel: PieChartViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        ViewModelProvider(this)[PieChartViewModel::class.java].also { pieChartViewModel = it }

        val pieChart = findViewById<PieChart>(R.id.pie_chart)
        val calculateButton = findViewById<Button>(R.id.calc_button)
        val resetButton = findViewById<Button>(R.id.reset_button)
        val inputData = findViewById<EditText>(R.id.sample_input)
        val noDataIcon = findViewById<ImageView>(R.id.no_data_img)

        initializeViews()

        pieChart.setNoDataText("")
        pieChart.setNoDataTextColor(0)
        pieChart.description = null

        // Set onClick listeners for buttons
        calculateButton.setOnClickListener {
            pieChartViewModel!!.processData(inputData, pieChart)
            if (inputData.error != null) noDataIcon.visibility =
                View.VISIBLE else noDataIcon.visibility = View.INVISIBLE
        }

        resetButton.setOnClickListener {
            pieChartViewModel!!.resetData(inputData, pieChart)
            noDataIcon.visibility = View.VISIBLE
        }
    }

    /**
     * Initializes the views and applies gradient text to certain TextViews.
     */
    private fun initializeViews() {

        val activityTitle = resources.getString(R.string.data_visualization_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        val activityDesc = resources.getString(R.string.pie_chart_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardTwoLabel = resources.getString(R.string.enter_data_title)
        val enterDataTextView = findViewById<TextView>(R.id.card_two_title)
        enterDataTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, cardTwoLabel, "#EC3CAB", "#0B40C5")
    }
}
