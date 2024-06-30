package com.blazewheeler.statellus.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.StatisticsModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import ru.noties.jlatexmath.JLatexMathView

/**
 * ViewModel for Sample Size Calculator functionality.
 */
class SampleSizeCalculatorViewModel: ViewModel() {

    /**
     * Sets default formulas for Latex views.
     *
     * @param jLatexMathView1 First Latex view.
     * @param jLatexMathView2 Second Latex view.
     */
    fun setDefaultFormulas(jLatexMathView1: JLatexMathView, jLatexMathView2: JLatexMathView){

        jLatexMathView1.setLatex("\\text{Sample Size} = \\frac{{z^2 \\cdot p \\cdot q}}{{E^2}}")
        jLatexMathView2.setLatex("\\text{Margin of Error} = z \\cdot \\frac{{\\sigma}}{{\\sqrt{n}}}")

    }

    /**
     * Initializes views in the activity.
     *
     * @param activity The activity in which views are initialized.
     */
    fun initializeViews(activity: AppCompatActivity) {

        val resources = activity.resources
        // Set titles and apply gradient text for visual styling
        val activityTitle = resources.getString(R.string.discrete_math_title)
        val activityTitleTextView = activity.findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        val activityDesc = resources.getString(R.string.sample_size_calculator_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardLabelOne = resources.getString(R.string.sample_size_title)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        val cardLabelTwo = resources.getString(R.string.margin_of_error_title)
        val cardTwoTextView = activity.findViewById<TextView>(R.id.card_two_title)
        cardTwoTextView.text = cardLabelTwo
        GradientTextUtil.applyGradientText(cardTwoTextView, cardLabelTwo,"#EC3CAB", "#0B40C5" )

        val cardThreeLabel = resources.getString(R.string.enter_data_title)
        val enterDataThreeTextView = activity.findViewById<TextView>(R.id.card_three_title)
        enterDataThreeTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataThreeTextView, cardThreeLabel, "#EC3CAB", "#0B40C5")

        val cardFourLabel = resources.getString(R.string.enter_data_title)
        val enterDataFourTextView = activity.findViewById<TextView>(R.id.card_four_title)
        enterDataFourTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataFourTextView, cardFourLabel, "#EC3CAB", "#0B40C5")
    }

    /**
     * Resets data based on selected radio button.
     *
     * @param activity The activity context.
     */
    fun resetData(activity: AppCompatActivity) {

        val sampleSizeFlag = activity.findViewById<RadioButton>(R.id.sample_size_radio_button).isChecked
        val sampleSizeSolutionSolutionTextView = activity.findViewById<TextView>(R.id.sample_size_solution_text_view)
        val marginOfErrorEditText = activity.findViewById<EditText>(R.id.margin_of_error_input)
        val populationProportionEditText = activity.findViewById<EditText>(R.id.population_proportion_input)

        val marginOfErrorFlag = activity.findViewById<RadioButton>(R.id.margin_of_error_radio_button).isChecked
        val marginOfErrorSolutionTextView = activity.findViewById<TextView>(R.id.margin_of_error_solution_text_view)
        val sampleSizeEditText = activity.findViewById<EditText>(R.id.sample_size_input)
        val marginPopulationProportionEditText  = activity.findViewById<EditText>(R.id.population_proportion_input_margin_of_error)

        if (sampleSizeFlag) {
            sampleSizeSolutionSolutionTextView.visibility = View.GONE
            marginOfErrorEditText.text.clear()
            populationProportionEditText.text.clear()
        }

        if (marginOfErrorFlag) {
            marginOfErrorSolutionTextView.visibility = View.GONE
            sampleSizeEditText.text.clear()
            marginPopulationProportionEditText.text.clear()
        }
    }

    /**
     * Processes margin of error data.
     *
     * @param confidenceLevel Confidence level.
     * @param sampleSize Sample size.
     * @param populationProportion Population proportion.
     * @param textView The TextView where result will be shown.
     */
    fun processMarginOfError(
        confidenceLevel: Double,
        sampleSize: EditText,
        populationProportion: EditText,
        textView: TextView
    ) {
        val sampleSizeText = sampleSize.text.toString()
        val populationProportionText = populationProportion.text.toString()

        try {

            if(sampleSize.text.isEmpty() || populationProportion.text.isEmpty())
                throw java.lang.NumberFormatException("Whoops! Input is empty. ")

            if(sampleSizeText.toDouble() < 1 || sampleSizeText.toDouble() > 100)
                throw  java.lang.NumberFormatException("Whoops! Enter value between 1 and 100")

            if(populationProportionText.toDouble() < 1 || populationProportionText.toDouble() > 100)
                throw java.lang.NumberFormatException("Whoops! Enter value between 1 and 100 ")

            val marginOfErrorSolution = StatisticsModel.calculateMarginOfError(
                confidenceLevel,
                String.format("%.2f", sampleSizeText.toDouble() * 0.01).toDouble(),
                String.format("%.2f", populationProportionText.toDouble() * 0.01).toDouble()
            )

            textView.visibility = View.VISIBLE
            textView.text = "Margin of Error = $marginOfErrorSolution %"



        } catch (e: NumberFormatException) {
            sampleSize.error = e.message
        }
    }

    /**
     * Processes sample size data.
     *
     * @param confidenceLevel Confidence level.
     * @param marginOfError Margin of error.
     * @param populationProportion Population proportion.
     * @param textView The TextView where result will be shown.
     */
    fun processSampleSize(
        confidenceLevel: Double,
        marginOfError: EditText,
        populationProportion: EditText,
        textView: TextView
    ){
        val marginOfErrorText = marginOfError.text.toString()
        val populationProportionText = populationProportion.text.toString()


        try {
            if (marginOfError.text.isEmpty() || populationProportion.text.isEmpty())
                throw java.lang.NumberFormatException("Whoops! Input is empty. ")

            if (marginOfErrorText.toDouble() < 1 || marginOfErrorText.toDouble() > 100)
                throw java.lang.NumberFormatException("Whoops! Enter value between 1 and 100")

            if (populationProportionText.toDouble() < 1 || populationProportionText.toDouble() > 100)
                throw java.lang.NumberFormatException("Whoops! Enter value between 1 and 100")

            val sampleSizeSolution = StatisticsModel.calculateSampleSize(
                confidenceLevel,
                String.format("%.2f", marginOfErrorText.toDouble() * 0.01).toDouble(),
                String.format("%.2f", populationProportionText.toDouble() *0.01).toDouble()
            )

            textView.visibility = View.VISIBLE
            textView.text = "Sample Size = $sampleSizeSolution"

        } catch (e: NumberFormatException) {
            marginOfError.error = e.message
        }
    }
}