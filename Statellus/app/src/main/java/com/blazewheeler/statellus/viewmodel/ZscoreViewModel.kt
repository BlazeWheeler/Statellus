package com.blazewheeler.statellus.viewmodel

import android.content.Context
import android.view.View

import android.widget.EditText

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.DiscreteMathModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import ru.noties.jlatexmath.JLatexMathView



class ZscoreViewModel : ViewModel() {

    /**
     * Initializes the views in the activity.
     *
     * @param activity The activity where views need to be initialized.
     */
    fun initializeViews(activity: AppCompatActivity) {

        val resources = activity.resources
        // Set titles and apply gradient text for visual styling
        val activityTitle = resources.getString(R.string.discrete_math_title)
        val activityTitleTextView = activity.findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        val activityDesc = resources.getString(R.string.z_score_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardLabelOne = resources.getString(R.string.z_score)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        val cardThreeLabel = resources.getString(R.string.enter_data_title)
        val enterDataThreeTextView = activity.findViewById<TextView>(R.id.card_three_title)
        enterDataThreeTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataThreeTextView, cardThreeLabel, "#EC3CAB", "#0B40C5")
    }

    /**
     * Sets default LaTeX formulas for arithmetic and geometric sequences.
     *
     * @param jLatexMathView1 The JLatexMathView for displaying the arithmetic sequence formula.
     * @param jLatexMathView2 The JLatexMathView for displaying the geometric sequence formula.
     */
    fun setDefaultFormulas(zScoreSolutionTextView: TextView, jLatexMathView1: JLatexMathView, context: Context) {
        jLatexMathView1.setLatex("Z = \\frac{{X - \\mu}}{{\\sigma}}")

        zScoreSolutionTextView.text = buildString {
            append("${context.getString(R.string.z_title)}\n")
            append("${context.getString(R.string.p_x_less_than_z)}\n")

            append("${context.getString(R.string.p_between_minus_z_and_z)}\n")
        }
    }


    fun processZscoreData(
        rawScore: EditText,
        populationMean: EditText,
        standardDeviation: EditText,
        solutionTextView: TextView,
        context: Context
    ) {
        val rawScoreText = rawScore.text.toString()
        val popMeanText = populationMean.text.toString()
        val standardDeviationText = standardDeviation.text.toString()

        try {
            if (rawScoreText.isEmpty() || popMeanText.isEmpty() || standardDeviationText.isEmpty()) {
                throw NumberFormatException("Input is empty.")
            }

            val rawScoreDouble = rawScoreText.toDouble()
            val stdDouble = standardDeviationText.toDouble()
            val popMeanDouble = popMeanText.toDouble()

            val arithmeticSequenceList: ArrayList<Double> = DiscreteMathModel.calculateProbabilities(
                rawScoreDouble,
                popMeanDouble,
                stdDouble
            )

            solutionTextView.text = """
            ${context.getString(R.string.z_title)} ${arithmeticSequenceList[0]}
            ${"P( x < $rawScoreText) = "} ${arithmeticSequenceList[1]}
            ${"P( $popMeanText < x < $rawScoreText) = "} ${arithmeticSequenceList[2]}
        """.trimIndent()
        } catch (e: NumberFormatException) {
            rawScore.error = e.message
        }
    }



    /**
     * Resets the data entered by the user.
     *
     * @param activity The activity containing the views to be reset.
     */
    fun restData(activity: AppCompatActivity) {

        val rawScoreEditText = activity.findViewById<EditText>(R.id.input_x).text.clear()
        val popMeanEditText = activity.findViewById<EditText>(R.id.input_pop_mean).text.clear()
        val stdEditText = activity.findViewById<EditText>(R.id.input_std).text.clear()

        setDefaultFormulas(activity.findViewById(R.id.z_score_solution), activity.findViewById(R.id.j_latex_math_view_1),  activity)
    }
}