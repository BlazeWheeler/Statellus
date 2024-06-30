package com.blazewheeler.statellus.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

import com.blazewheeler.statellus.R


import com.blazewheeler.statellus.viewmodel.SampleSizeCalculatorViewModel
import ru.noties.jlatexmath.JLatexMathView

/**
 * Activity for the Sample Size Calculator functionality.
 */
class SampleSizeCalculatorView : AppCompatActivity() {

    private val viewModel: SampleSizeCalculatorViewModel by viewModels()

    private lateinit var sampleSizeRadioButton: RadioButton
    private lateinit var marginOfErrorRadioButton: RadioButton
    private lateinit var sampleSizeViewsContainer: CardView
    private lateinit var marginOfErrorViewsContainer: CardView
    private lateinit var sampleSizeInputViewsContainer: CardView
    private lateinit var marginOfErrorInputViewsContainer: CardView
    private lateinit var sampleSizeSolution: JLatexMathView
    private lateinit var marginOfErrorSolution: JLatexMathView
    private lateinit var sampleSizeSolutionTextView: TextView
    private lateinit var marginOfErrorSolutionTextView: TextView
    private lateinit var confidenceLevelSpinner: Spinner
    private lateinit var marginOfErrorSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_size_calculator_view)

        // Initialize views
        sampleSizeRadioButton = findViewById(R.id.sample_size_radio_button)
        marginOfErrorRadioButton = findViewById(R.id.margin_of_error_radio_button)
        sampleSizeViewsContainer = findViewById(R.id.sample_size_card_view)
        marginOfErrorViewsContainer = findViewById(R.id.margin_of_error_card_view)
        sampleSizeInputViewsContainer = findViewById(R.id.sample_size_input_card_view)
        marginOfErrorInputViewsContainer = findViewById(R.id.margin_of_error_input_card_view)
        sampleSizeSolution = findViewById(R.id.j_latex_math_view_1)
        marginOfErrorSolution = findViewById(R.id.j_latex_math_view_2)
        sampleSizeSolutionTextView = findViewById(R.id.sample_size_solution_text_view)
        marginOfErrorSolutionTextView = findViewById(R.id.margin_of_error_solution_text_view)
        confidenceLevelSpinner = findViewById(R.id.confidence_level_spinner)
        marginOfErrorSpinner = findViewById(R.id.confidence_level_spinner_margin_error)

        var confidenceLevel = 0.0

        // Set listeners for spinners
        marginOfErrorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = adapterView?.getItemAtPosition(position).toString().replace("%", "")
                confidenceLevel = String.format("%.2f", selectedItem.toDouble() * 0.01).toDouble()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                confidenceLevel = String.format("%.2f", .70).toDouble()
            }
        }

        confidenceLevelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = adapterView?.getItemAtPosition(position).toString().replace("%", "")
                confidenceLevel = String.format("%.2f", selectedItem.toDouble() * 0.01).toDouble()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                confidenceLevel = String.format("%.2f", .70).toDouble()
            }
        }

        // Initialize ViewModel and set default formulas
        viewModel.initializeViews(this)
        viewModel.setDefaultFormulas(sampleSizeSolution, marginOfErrorSolution)

        // Set default visibility for radio buttons and input views
        sampleSizeRadioButton.isChecked = true
        marginOfErrorViewsContainer.visibility = View.GONE
        sampleSizeSolutionTextView.visibility = View.GONE
        marginOfErrorSolutionTextView.visibility = View.GONE
        sampleSizeInputViewsContainer.visibility = View.VISIBLE
        marginOfErrorInputViewsContainer.visibility = View.GONE

        // Set listener for radio button group
        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sample_size_radio_button -> {
                    sampleSizeViewsContainer.visibility = View.VISIBLE
                    marginOfErrorViewsContainer.visibility = View.GONE
                    sampleSizeInputViewsContainer.visibility = View.VISIBLE
                    marginOfErrorInputViewsContainer.visibility = View.GONE
                }
                R.id.margin_of_error_radio_button -> {
                    sampleSizeViewsContainer.visibility = View.GONE
                    marginOfErrorViewsContainer.visibility = View.VISIBLE
                    sampleSizeInputViewsContainer.visibility = View.GONE
                    marginOfErrorInputViewsContainer.visibility = View.VISIBLE
                }
            }
        }

        // Set listeners for calculation and reset buttons
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            if (sampleSizeRadioButton.isChecked) {
                viewModel.processSampleSize(
                    confidenceLevel,
                    findViewById(R.id.margin_of_error_input),
                    findViewById(R.id.population_proportion_input),
                    findViewById(R.id.sample_size_solution_text_view)
                )
            }
            if (marginOfErrorRadioButton.isChecked) {
                viewModel.processMarginOfError(
                    confidenceLevel,
                    findViewById(R.id.sample_size_input),
                    findViewById(R.id.population_proportion_input_margin_of_error),
                    findViewById(R.id.margin_of_error_solution_text_view)
                )
            }
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            viewModel.resetData(this)
        }
    }
}