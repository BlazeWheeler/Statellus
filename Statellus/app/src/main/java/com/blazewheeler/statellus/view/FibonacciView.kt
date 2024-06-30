package com.blazewheeler.statellus.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.viewmodel.FibonacciViewModel
import ru.noties.jlatexmath.JLatexMathView

/**
 * View for the Fibonacci Calculator functionality.
 */
class FibonacciView : AppCompatActivity() {

    private val viewModel: FibonacciViewModel by viewModels()

    private lateinit var singleNumberRadioButton : RadioButton
    private lateinit var sequenceRadioButton : RadioButton
    private lateinit var singleNumberViewsContainer : CardView
    private lateinit var sequenceViewsContainer: CardView
    private lateinit var singleNumberInputViewsContainer : CardView
    private lateinit var sequenceInputViewsContainer : CardView
    private lateinit var singleNumberSolution : JLatexMathView
    private lateinit var sequenceSolution : JLatexMathView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci_view)

        // Initialize UI Elements
        singleNumberRadioButton = findViewById(R.id.single_number_radio_button)
        sequenceRadioButton = findViewById(R.id.sequence_radio_button)
        singleNumberViewsContainer = findViewById(R.id.single_number_card_view)
        sequenceViewsContainer = findViewById(R.id.sequence_card_view)
        singleNumberInputViewsContainer = findViewById(R.id.single_number_input_card_view)
        sequenceInputViewsContainer = findViewById(R.id.sequence_input_card_view)
        singleNumberSolution = findViewById(R.id.j_latex_math_view_1)
        sequenceSolution = findViewById(R.id.j_latex_math_view_2)

        // Initialize view model
        viewModel.initializeViews(this)
        viewModel.setDefaultFormulas(singleNumberSolution, sequenceSolution)

        // Set default radio button selection
        singleNumberRadioButton.isChecked = true
        sequenceViewsContainer.visibility = View.GONE
        sequenceInputViewsContainer.visibility = View.INVISIBLE

        // Set up the OnCheckedChangeListener for the RadioGroup
        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { /*RadioGroup*/_, checkedId ->
            when (checkedId) {
                R.id.single_number_radio_button -> {
                    singleNumberViewsContainer.visibility = View.VISIBLE
                    singleNumberInputViewsContainer.visibility = View.VISIBLE
                    findViewById<TextView>(R.id.single_sequence_text_view).visibility = View.GONE
                    findViewById<TextView>(R.id.single_solution_text_view).visibility = View.GONE
                    sequenceViewsContainer.visibility = View.GONE
                    sequenceInputViewsContainer.visibility = View.GONE
                    findViewById<EditText>(R.id.input_n1).text.clear()
                    findViewById<EditText>(R.id.input_n2).text.clear()
                    viewModel.setDefaultFormulas(singleNumberSolution, sequenceSolution)
                    findViewById<TextView>(R.id.single_sequence_text_view).text = ""
                    findViewById<TextView>(R.id.single_solution_text_view).text = ""
                }
                R.id.sequence_radio_button -> {
                    singleNumberViewsContainer.visibility = View.GONE
                    singleNumberInputViewsContainer.visibility = View.GONE
                    findViewById<TextView>(R.id.single_sequence_text_view).visibility = View.GONE
                    findViewById<TextView>(R.id.single_solution_text_view).visibility = View.GONE
                    sequenceViewsContainer.visibility = View.VISIBLE
                    sequenceInputViewsContainer.visibility = View.VISIBLE
                    viewModel.setDefaultFormulas(singleNumberSolution, sequenceSolution)
                    findViewById<EditText>(R.id.single_number_input).text.clear()
                    viewModel.setDefaultFormulas(singleNumberSolution, sequenceSolution)
                    findViewById<TextView>(R.id.single_sequence_text_view).text = ""
                    findViewById<TextView>(R.id.single_solution_text_view).text = ""
                }
            }
        }

        // Calculate button click listener
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            if(singleNumberRadioButton.isChecked)
                viewModel.singleNumErrorHandling(findViewById(R.id.single_number_input), singleNumberSolution, findViewById(R.id.single_solution_text_view))
            else if (sequenceRadioButton.isChecked)
                viewModel.sequenceNumErrorHandling(findViewById(R.id.input_n1), findViewById(R.id.input_n2), sequenceSolution,findViewById(R.id.single_sequence_text_view))
        }

        // Reset button click listener
        findViewById<Button>(R.id.reset_button).setOnClickListener {
            viewModel.setDefaultFormulas(
                jLatexMathView1 = findViewById(R.id.j_latex_math_view_1),
                jLatexMathView2 = findViewById(R.id.j_latex_math_view_2)
            )
            findViewById<EditText>(R.id.input_n1).text.clear()
            findViewById<EditText>(R.id.input_n2).text.clear()
            findViewById<EditText>(R.id.single_number_input).text.clear()
            findViewById<TextView>(R.id.single_solution_text_view).visibility = View.GONE
            findViewById<TextView>(R.id.single_sequence_text_view).visibility = View.GONE
        }
    }
}
