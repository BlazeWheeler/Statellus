package com.blazewheeler.statellus.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blazewheeler.statellus.R
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.viewmodel.SimpleLineChartViewModel
import com.github.mikephil.charting.charts.LineChart


/**
 * An activity to display a simple line chart.
 */
class SimpleLineChartActivity : AppCompatActivity() {

    private val viewModel: SimpleLineChartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_line_chart)


        val lineChart = findViewById<LineChart>(R.id.line_chart)
        val calculateButton = findViewById<Button>(R.id.calc_button)
        val resetButton = findViewById<Button>(R.id.reset_button)
        val inputData = findViewById<EditText>(R.id.sample_input)
        val noDataIcon = findViewById<ImageView>(R.id.no_data_img)
        lineChart.visibility = View.INVISIBLE

        initializeViews()


        // Set onClick listeners for buttons
        calculateButton.setOnClickListener {
            viewModel.processData(inputData, lineChart)
            if (inputData.error != null) noDataIcon.visibility =
                View.VISIBLE else noDataIcon.visibility = View.INVISIBLE
        }

        resetButton.setOnClickListener {
            viewModel.resetData(inputData, lineChart)
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

        val activityDesc = resources.getString(R.string.simple_line_chart_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardTwoLabel = resources.getString(R.string.enter_data_title)
        val enterDataTextView = findViewById<TextView>(R.id.card_two_title)
        enterDataTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, cardTwoLabel, "#EC3CAB", "#0B40C5")
    }






}
