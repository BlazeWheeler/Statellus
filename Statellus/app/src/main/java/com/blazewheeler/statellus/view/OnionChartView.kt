package com.blazewheeler.statellus.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.puskal.oniondiagram.OnionDiagramView

class OnionChartView : AppCompatActivity() {

    //TODO: Create Viewmodel

    private lateinit var onionDiagramView: OnionDiagramView
    private lateinit var calculateButton: Button
    private lateinit var resetButton: Button
    private lateinit var inputData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onion_chart_view)

        onionDiagramView = findViewById(R.id.view_onion)
        calculateButton = findViewById(R.id.calc_button)
        resetButton = findViewById(R.id.reset_button)
        inputData = findViewById(R.id.sample_input)

        initializeViews()

        onionDiagramView.colorList = arrayListOf(

            ContextCompat.getColor(this, R.color.purple_50),
            ContextCompat.getColor(this, R.color.purple_100),
            ContextCompat.getColor(this, R.color.purple_200),
            ContextCompat.getColor(this, R.color.purple_300),
            ContextCompat.getColor(this, R.color.purple_400),

            ContextCompat.getColor(this, R.color.purple_A100),
            ContextCompat.getColor(this, R.color.purple_A200),
            ContextCompat.getColor(this, R.color.purple_A400),
        )
        onionDiagramView.valueList=arrayListOf(1F)

        calculateButton.setOnClickListener {
            generateOnionDiagram()
        }

        resetButton.setOnClickListener {
            resetOnionDiagram()
        }
    }

    private fun generateOnionDiagram() {
        val dataString = inputData.text.toString()
        onionDiagramView.invalidate()
        // Testing : Log.d("myTag", inputData.text.toString())
        // TODO: no more than 9 values : Done
        // TODO: Think of solution for too big of rang EX: 2 and 50

        try {
            if (TextUtils.isEmpty(dataString)) {
                inputData.error = "Enter Values"
            } else {
                val separatedValues = dataString.split(",").map { it.trim().toFloat() }
                if (separatedValues.size > 7) {
                    inputData.error = " 7 elements may create a cluttered chart. Try using a histogram for bigger data sets. "
                } else {
                    onionDiagramView.valueList = ArrayList(separatedValues)
                    //Testing:  Log.d("MyTag2", separatedValues.toString())
                }
            }
        } catch (e: NumberFormatException) {
            inputData.error = "Enter only comma-separated numeric values"
        }
    }

    private fun resetOnionDiagram() {
        onionDiagramView.valueList = ArrayList()
        inputData.text.clear()
        onionDiagramView.invalidate()
        onionDiagramView.valueList=arrayListOf(1F)
    }

    private fun initializeViews() {
        /* Set activity title */
        val activityTitle = resources.getString(R.string.data_visualization_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        /* Set activity title */
        val activityDesc = resources.getString(R.string.stem_and_leaf_plot_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        /* Set card Three label */
        val enterDataTextView = findViewById<TextView>(R.id.card_two_title)
        enterDataTextView.text = getString(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, getString(R.string.enter_data_title), "#EC3CAB", "#0B40C5")
    }
}