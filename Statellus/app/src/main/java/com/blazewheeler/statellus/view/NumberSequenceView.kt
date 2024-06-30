package com.blazewheeler.statellus.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.viewmodel.NumberSequenceViewModel
import ru.noties.jlatexmath.JLatexMathView

/**
 * Activity class for handling number sequence view.
 */
class NumberSequenceView : AppCompatActivity() {

    private val viewModel: NumberSequenceViewModel by viewModels()

    private lateinit var arithmeticRadioButton : RadioButton
    private lateinit var geometricRadioButton : RadioButton
    private lateinit var arithmeticViewsContainer : CardView
    private lateinit var geometricViewsContainer: CardView
    private lateinit var arithmeticInputViewsContainer : CardView
    private lateinit var geometricInputViewsContainer : CardView
    private lateinit var arithmeticSolution : JLatexMathView
    private lateinit var geometricSolution : JLatexMathView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_sequence_view)

        arithmeticRadioButton = findViewById(R.id.arithmetic_radio_button)
        geometricRadioButton = findViewById(R.id.geometric_radio_button)
        arithmeticViewsContainer = findViewById(R.id.arithmetic_card_view)
        geometricViewsContainer = findViewById(R.id.geometric_card_view)
        arithmeticInputViewsContainer = findViewById(R.id.arithmetic_input_card_view)
        geometricInputViewsContainer = findViewById(R.id.geometric_input_card_view)
        arithmeticSolution = findViewById(R.id.j_latex_math_view_1)
        geometricSolution = findViewById(R.id.j_latex_math_view_2)

        viewModel.initializeViews(this)
        viewModel.setDefaultFormulas(arithmeticSolution, geometricSolution)

        arithmeticRadioButton.isChecked = true
        geometricViewsContainer.visibility = View.GONE
        geometricInputViewsContainer.visibility = View.INVISIBLE

        this.findViewById<TextView>(R.id.arithmetic_solution_text_view).visibility = View.GONE
        this.findViewById<TextView>(R.id.geometric_solution_text_view).visibility = View.GONE

        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { /*RadioGroup*/_, checkedId ->
            when (checkedId) {
                R.id.arithmetic_radio_button -> {

                    arithmeticViewsContainer.visibility = View.VISIBLE
                    arithmeticInputViewsContainer.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.geometric_solution_text_view).visibility = View.GONE
                    findViewById<TextView>(R.id.arithmetic_solution_text_view).visibility = View.GONE

                    geometricViewsContainer.visibility = View.GONE
                    geometricInputViewsContainer.visibility = View.GONE
                }

                R.id.geometric_radio_button -> {

                    arithmeticViewsContainer.visibility = View.GONE
                    arithmeticInputViewsContainer.visibility = View.GONE

                    findViewById<TextView>(R.id.arithmetic_solution_text_view).visibility = View.GONE
                    findViewById<TextView>(R.id.geometric_solution_text_view).visibility = View.GONE

                    geometricViewsContainer.visibility = View.VISIBLE
                    geometricInputViewsContainer.visibility = View.VISIBLE
                }
            }
        }

        findViewById<Button>(R.id.calc_button).setOnClickListener {
            if(arithmeticRadioButton.isChecked)
                viewModel.processArithmeticData(
                    findViewById(R.id.input_std),
                    findViewById(R.id.input_pop_mean),
                    findViewById(R.id.input_x),
                    findViewById(R.id.arithmetic_solution_text_view)
                )

            else if (geometricRadioButton.isChecked)
                viewModel.processGeometricData(
                    findViewById(R.id.input_an),
                    findViewById(R.id.input_r),
                    findViewById(R.id.input_n),
                    findViewById(R.id.geometric_solution_text_view)
                )
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {

            viewModel.setDefaultFormulas(
                jLatexMathView1 = findViewById(R.id.j_latex_math_view_1),
                jLatexMathView2 = findViewById(R.id.j_latex_math_view_2)
            )

            viewModel.restData(this)
        }
    }
}