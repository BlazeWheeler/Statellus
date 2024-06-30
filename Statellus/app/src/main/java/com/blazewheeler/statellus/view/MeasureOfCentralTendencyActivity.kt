package com.blazewheeler.statellus.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup


import androidx.activity.viewModels
import androidx.cardview.widget.CardView

import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.viewmodel.MeasureOfCentralTendencyViewModel
import ru.noties.jlatexmath.JLatexMathView

/**
 * This activity allows users to calculate measures of central tendency,
 * including mean, median, and mode, for both sample and population data.
 * Users can input data, and the activity displays the calculated results.
 */
class MeasureOfCentralTendencyActivity : AppCompatActivity() {

    //TODO: Account for big numbers ex:10000000000

    // ViewModel for this activity
    private val viewModel: MeasureOfCentralTendencyViewModel by viewModels()

    private lateinit var sampleRadioButton : RadioButton
    private lateinit var populationRadioButton : RadioButton
    private lateinit var sampleViewsContainer : CardView
    private lateinit var populationViewsContainer : CardView

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_of_central_tendency)

        // Initialize UI elements
        sampleRadioButton = findViewById(R.id.sample_radio_button)
        populationRadioButton = findViewById(R.id.population_radio_button)
        sampleViewsContainer = findViewById(R.id.sample_set_card_view)
        populationViewsContainer = findViewById(R.id.population_set_card_view)



        viewModel.latexFormulas.observe(this) { formulas ->
            formulas.forEachIndexed { index, formula ->
                findViewById<JLatexMathView>(
                    resources.getIdentifier(
                        "j_latex_math_view_${index + 1}",
                        "id",
                        packageName
                    )
                )
                    .setLatex(formula)
            }
        }
        viewModel.initializeViews(this)
        populationViewsContainer.visibility = View.GONE

        // Set up the OnCheckedChangeListener for the RadioGroup
        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { /*RadioGroup*/_, checkedId ->
            when (checkedId) {
                R.id.sample_radio_button -> {
                    // Sample radio button is checked
                    // Show views related to sample data
                    sampleViewsContainer.visibility = View.VISIBLE
                    populationViewsContainer.visibility = View.GONE

                }
                R.id.population_radio_button -> {
                    // Population radio button is checked
                    // Show views related to population data
                    sampleViewsContainer.visibility = View.GONE
                    populationViewsContainer.visibility = View.VISIBLE
                }
            }
        }

        // Set click listener for calculate button
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            // Process data for both sample and population
            viewModel.processData(
                findViewById(R.id.sample_input),
                findViewById(R.id.j_latex_math_view_1),
                findViewById(R.id.j_latex_math_view_2),
                findViewById(R.id.j_latex_math_view_3),
                findViewById(R.id.j_latex_math_view_4),
                findViewById(R.id.j_latex_math_view_5),
                findViewById(R.id.j_latex_math_view_6)
            )
        }

        // Set click listener for reset button
        findViewById<Button>(R.id.reset_button).setOnClickListener {
            // Reset UI elements
            viewModel.provideDefaultFormula()
            findViewById<EditText>(R.id.sample_input).text.clear()
        }
    }
}
