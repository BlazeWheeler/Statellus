package com.blazewheeler.statellus.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.ProbabilityModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import ru.noties.jlatexmath.JLatexMathView
import java.math.BigDecimal

class BayesTheoremViewModel: ViewModel()  {

    fun initializeViews(activity: AppCompatActivity) {

        val resources = activity.resources
        // Set titles and apply gradient text for visual styling
        val activityTitle = resources.getString(R.string.probability_title)
        val activityTitleTextView = activity.findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        val activityDesc = resources.getString(R.string.bayes_theorem_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")


        val cardLabelOne = resources.getString(R.string.p_a_b)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )


        // P(A) Enter Data title
        val cardLabelA = resources.getString(R.string.enter_data_title)
        val pATextView = activity.findViewById<TextView>(R.id.card_five_title)
        pATextView.text = cardLabelA
        GradientTextUtil.applyGradientText(pATextView, cardLabelA, "#EC3CAB", "#0B40C5")


        // p(A) Solution Title
        val cardLabelTopPA = resources.getString(R.string.p_a)
        val pACardTextView = activity.findViewById<TextView>(R.id.card_p_a_title)
        pACardTextView.text = cardLabelTopPA
        GradientTextUtil.applyGradientText(pACardTextView, cardLabelTopPA, "#EC3CAB", "#0B40C5")

        // P(B|A) Enter Data title
        val cardLabelTopPbGivenA = resources.getString(R.string.p_b_a)
        val pBGivenATextView = activity.findViewById<TextView>(R.id.card_two_title)
        pBGivenATextView.text = cardLabelTopPbGivenA
        GradientTextUtil.applyGradientText(pBGivenATextView, cardLabelTopPbGivenA, "#EC3CAB", "#0B40C5")

        // P(A|B) Enter Data title
        val cardLabelTwo = resources.getString(R.string.enter_data_title)
        val cardTwoTextView = activity.findViewById<TextView>(R.id.card_three_title)
        cardTwoTextView.text = cardLabelTwo
        GradientTextUtil.applyGradientText(cardTwoTextView, cardLabelTwo,"#EC3CAB", "#0B40C5" )

        // P(B|A) Enter Data title
        val cardLabelThree = resources.getString(R.string.enter_data_title)
        val cardThreeTextView = activity.findViewById<TextView>(R.id.card_four_title)
        cardThreeTextView.text = cardLabelThree
        GradientTextUtil.applyGradientText(cardThreeTextView, cardLabelThree,"#EC3CAB", "#0B40C5" )
    }

    fun setDefaultFormulas(jLatexMathView1: JLatexMathView, jLatexMathView2: JLatexMathView,
                           jLatexMathView3: JLatexMathView, jLatexMathView4: JLatexMathView)
    {
        jLatexMathView1.setLatex("P(A|B) = \\frac{P(B|A) \\times P(A)}{P(B)}")
        jLatexMathView2.setLatex("P(B|A) = \\frac{P(A|B) \\times P(B)}{P(A)}")
        jLatexMathView3.setLatex("P(A) = \\frac{P(B) \\times P(A|B)}{P(B|A)}")
        jLatexMathView4.setLatex("P(B) = \\frac{P(B|A) \\times P(A)}{P(A|B)}")

    }

    fun restData(activity: AppCompatActivity) {

        // P(A|B) CardView
        val bayesTheoremSolutionTextView = activity.findViewById<TextView>(R.id.bayes_solution)
        val pAEditText = activity.findViewById<EditText>(R.id.row_1_p_a)
        val pBEditText = activity.findViewById<EditText>(R.id.row_1_p_b)
        val pbAEditText = activity.findViewById<EditText>(R.id.row_1_p_b_a)
        bayesTheoremSolutionTextView.visibility = View.GONE
        pAEditText.text.clear()
        pBEditText.text.clear()
        pbAEditText.text.clear()

        // P(B|A) CardView
        val bayesTheoremSolutionTextView2 = activity.findViewById<TextView>(R.id.bayes_solution_2)
        val pAEditText2 = activity.findViewById<EditText>(R.id.row_4_p_a)
        val pBEditText2 = activity.findViewById<EditText>(R.id.row_4_p_b)
        val pAbEditText2 = activity.findViewById<EditText>(R.id.row_4_p_a_b)
        bayesTheoremSolutionTextView2.visibility = View.GONE
        pAEditText2.text.clear()
        pBEditText2.text.clear()
        pAbEditText2.text.clear()


        // P(A) CardView
        val bayesTheoremSolutionTextView3 = activity.findViewById<TextView>(R.id.bayes_solution_3)
        val pAEditText3 = activity.findViewById<EditText>(R.id.row_5_p_a)
        val pBEditText3 = activity.findViewById<EditText>(R.id.row_5_p_b)
        val pAbEditText3 = activity.findViewById<EditText>(R.id.row_5_p_a_b)
        bayesTheoremSolutionTextView3.visibility = View.GONE
        pAEditText3.text.clear()
        pBEditText3.text.clear()
        pAbEditText3.text.clear()

    }

    fun processData(
        inputEditText1:EditText,
        inputEditText2: EditText,
        inputEditText3: EditText,
        solutionTextView: TextView,
        activity: AppCompatActivity
    ) {
        val pAEditTextString = inputEditText1.text.toString()
        val pBEditTextString = inputEditText3.text.toString()
        val pAbEditTextString = inputEditText2.text.toString()

        try {
            if(inputEditText1.text.isEmpty() || inputEditText3.text.isEmpty() || inputEditText2.text.isEmpty())
                throw java.lang.NumberFormatException("Whoops! Input is empty.")
            if(pAbEditTextString.toDouble() < 1 || pAbEditTextString.toDouble() > 100)
                throw java.lang.NumberFormatException("Whoops! Please enter value between 1 and 100")
            if(pBEditTextString.toDouble() < 1 || pBEditTextString.toDouble() > 100)
                throw java.lang.NumberFormatException("Whoops! Please enter value between 1 and 100")


            if (activity.findViewById<RadioButton>(R.id.p_a_b_radio_button).isChecked){
                val bayesSolution = ProbabilityModel.calculateBayesianProbabilityPbGivenA(
                    BigDecimal(pAEditTextString),
                    BigDecimal(pAbEditTextString),
                    BigDecimal(pBEditTextString)
                )
                solutionTextView.visibility = View.VISIBLE
                solutionTextView.text = "P(A|B) = $bayesSolution % "
            }

            if (activity.findViewById<RadioButton>(R.id.p_b_a_radio_button).isChecked) {
                val bayesSolution = ProbabilityModel.calculateBayesianProbabilityPaGivenB(
                    BigDecimal(pAEditTextString),
                    BigDecimal(pAbEditTextString),
                    BigDecimal(pBEditTextString)
                )
                solutionTextView.visibility = View.VISIBLE
                solutionTextView.text = "P(B|A) = $bayesSolution % "
            }


            if (activity.findViewById<RadioButton>(R.id.p_a_radio_button).isChecked) {
                val bayesSolution = ProbabilityModel.calculateBayesianProbabilityPa(
                    BigDecimal(pAEditTextString),
                    BigDecimal(pAbEditTextString),
                    BigDecimal(pBEditTextString)
                )
                solutionTextView.visibility = View.VISIBLE
                solutionTextView.text = "P(B|A) = $bayesSolution % "
            }
            
        }catch (e: NumberFormatException) {
            inputEditText1.error = e.message
        }
    }
}