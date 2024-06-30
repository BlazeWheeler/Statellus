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
import com.blazewheeler.statellus.viewmodel.BayesTheoremViewModel

class BayesTheoremView : AppCompatActivity() {

    private val viewModel: BayesTheoremViewModel by viewModels()

    // P(A|B) Components
    private lateinit var pAGivenBButton: RadioButton
    private lateinit var paBViewsContainer: CardView
    private lateinit var pAbSolutionViewsContainer: CardView
    private lateinit var bayesSolutionTextView : TextView

    // P(B|A) Components
    private lateinit var pBGivenAButton: RadioButton
    private lateinit var pBaViewsContainer: CardView
    private lateinit var pBaSolutionViewsContainer: CardView
    private lateinit var bayesSolutionTextView2: TextView

    // P(A) Components
    private lateinit var pARadioButton: RadioButton
    private lateinit var pAViewsContainer: CardView
    private lateinit var pASolutionViewsContainer: CardView
    private lateinit var bayesSolutionTextView3: TextView

    // P(B) Components
    private lateinit var pBRadioButton: RadioButton
    private lateinit var pBViewsContainer: CardView
    private lateinit var pBSolutionViewsContainer: CardView
    private lateinit var bayesSolutionTextView4: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayes_theorem_view)

        pAGivenBButton = findViewById(R.id.p_a_b_radio_button)
        pBGivenAButton = findViewById(R.id.p_b_a_radio_button)
        bayesSolutionTextView = findViewById(R.id.bayes_solution)
        bayesSolutionTextView2 = findViewById(R.id.bayes_solution_2)
        paBViewsContainer = findViewById(R.id.p_a_b_cardview_2)
        pBaViewsContainer = findViewById(R.id.p_b_a_cardview_3)
        pAbSolutionViewsContainer = findViewById(R.id.p_a_b_card_view)
        pBaSolutionViewsContainer = findViewById(R.id.p_b_a_card_view)

        // P(A) Components
        pARadioButton = findViewById(R.id.p_a_radio_button)
        pAViewsContainer = findViewById(R.id.p_a_card_view)
        pASolutionViewsContainer = findViewById(R.id.p_a_cardview_5)
        bayesSolutionTextView3 = findViewById(R.id.bayes_solution_3)
        bayesSolutionTextView3.visibility = View.GONE
        pASolutionViewsContainer.visibility = View.GONE
        pAViewsContainer.visibility = View.GONE

        // P(B) Components
        pBRadioButton = findViewById(R.id.p_b_radio_button)
        pBViewsContainer = findViewById(R.id.p_b_card_view)
        pBSolutionViewsContainer = findViewById(R.id.p_b_cardview_6)
        bayesSolutionTextView4 = findViewById(R.id.bayes_solution_4)
        bayesSolutionTextView4.visibility = View.GONE
        pBSolutionViewsContainer.visibility = View.GONE
        pBViewsContainer.visibility = View.GONE


        pBaSolutionViewsContainer.visibility = View.GONE
        pAbSolutionViewsContainer.visibility = View.VISIBLE
        pAGivenBButton.isChecked = true
        paBViewsContainer.visibility = View.VISIBLE
        pBaViewsContainer.visibility = View.GONE

        viewModel.initializeViews(this)

        viewModel.setDefaultFormulas(
            findViewById(R.id.j_latex_math_view_1),
            findViewById(R.id.j_latex_math_view_2),
            findViewById(R.id.j_latex_math_view_3),
            findViewById(R.id.j_latex_math_view_4)
        )

        bayesSolutionTextView.visibility = View.GONE
        bayesSolutionTextView2.visibility= View.GONE


        findViewById<RadioGroup>(R.id.radio_group).setOnCheckedChangeListener { /*RadioGroup*/_, checkedId ->
            when (checkedId) {

                R.id.p_a_radio_button -> {

                    viewModel.restData(this)

                    pAViewsContainer.visibility = View.VISIBLE
                    paBViewsContainer.visibility = View.GONE
                    pBaViewsContainer.visibility = View.GONE
                    pBViewsContainer.visibility = View.GONE

                    pASolutionViewsContainer.visibility = View.VISIBLE
                    pAbSolutionViewsContainer.visibility = View.GONE
                    pBaSolutionViewsContainer.visibility = View.GONE
                    pBSolutionViewsContainer.visibility = View.GONE
                }

                R.id.p_b_radio_button -> {

                    viewModel.restData(this)

                    pBViewsContainer.visibility = View.VISIBLE
                    paBViewsContainer.visibility = View.GONE
                    pBaViewsContainer.visibility = View.GONE
                    pAViewsContainer.visibility = View.GONE

                    pBSolutionViewsContainer.visibility = View.VISIBLE
                    pAbSolutionViewsContainer.visibility = View.GONE
                    pBaSolutionViewsContainer.visibility = View.GONE
                    pASolutionViewsContainer.visibility = View.GONE
                }

                R.id.p_a_b_radio_button -> {

                    viewModel.restData(this)

                    pAbSolutionViewsContainer.visibility = View.VISIBLE
                    pBaSolutionViewsContainer.visibility = View.GONE
                    pASolutionViewsContainer.visibility - View.GONE
                    pBSolutionViewsContainer.visibility = View.GONE

                    paBViewsContainer.visibility = View.VISIBLE
                    pBaViewsContainer.visibility = View.GONE
                    pAViewsContainer.visibility = View.GONE
                    pBViewsContainer.visibility = View.GONE

                }

                R.id.p_b_a_radio_button -> {

                     viewModel.restData(this)

                     pBaSolutionViewsContainer.visibility = View.VISIBLE
                     pAbSolutionViewsContainer.visibility = View.GONE
                     pASolutionViewsContainer.visibility = View.GONE
                     pBSolutionViewsContainer.visibility = View.GONE

                    pBaViewsContainer.visibility = View.VISIBLE
                    paBViewsContainer.visibility = View.GONE
                    pAViewsContainer.visibility = View.GONE
                    pBViewsContainer.visibility = View.GONE
                }
            }

        }


        findViewById<Button>(R.id.calc_button).setOnClickListener {


            if (findViewById<RadioButton>(R.id.p_a_b_radio_button).isChecked) {
                viewModel.processData(
                    findViewById(R.id.row_1_p_a),
                    findViewById(R.id.row_1_p_b_a),
                    findViewById(R.id.row_1_p_b),
                    bayesSolutionTextView,
                    this
                )
            }

            if (findViewById<RadioButton>(R.id.p_b_a_radio_button).isChecked) {
                viewModel.processData(
                    findViewById(R.id.row_4_p_a),
                    findViewById(R.id.row_4_p_a_b),
                    findViewById(R.id.row_4_p_b),
                    bayesSolutionTextView2,
                    this
                )
            }

            if (findViewById<RadioButton>(R.id.p_a_radio_button).isChecked) {
                viewModel.processData(
                    findViewById(R.id.row_5_p_a),
                    findViewById(R.id.row_5_p_b),
                    findViewById(R.id.row_5_p_a_b),
                    bayesSolutionTextView3,
                    this
                )
            }
        }



        findViewById<Button>(R.id.reset_button).setOnClickListener {
            viewModel.restData(this)
        }
    }
}