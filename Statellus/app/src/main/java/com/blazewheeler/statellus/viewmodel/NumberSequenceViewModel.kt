package com.blazewheeler.statellus.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.DiscreteMathModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.utils.StringUtils
import ru.noties.jlatexmath.JLatexMathView
import java.math.BigDecimal


/**
 * ViewModel class for handling number sequence operations.
 */
class NumberSequenceViewModel : ViewModel() {

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

        val activityDesc = resources.getString(R.string.number_sequence_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardLabelOne = resources.getString(R.string.arithmetic_sequence_title)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        val cardLabelTwo = resources.getString(R.string.geometric_sequence_title)
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
     * Sets default LaTeX formulas for arithmetic and geometric sequences.
     *
     * @param jLatexMathView1 The JLatexMathView for displaying the arithmetic sequence formula.
     * @param jLatexMathView2 The JLatexMathView for displaying the geometric sequence formula.
     */
    fun setDefaultFormulas(jLatexMathView1: JLatexMathView, jLatexMathView2: JLatexMathView){

        jLatexMathView1.setLatex("S_n = \\frac{n}{2} \\left(2a_1 + (n - 1)d\\right)\\\\\\\\a_n = a_1 + (n - 1)d")
        jLatexMathView2.setLatex(" S_n = \\frac{a_1 \\cdot (1 - r^n)}{1 - r}\\\\\\\\ a_n = a_1 \\cdot r^{n-1}")

    }

    /**
     * Processes user input for arithmetic sequence data.
     *
     * @param firstElement The EditText for the first element of the sequence.
     * @param commonDifference The EditText for the common difference of the sequence.
     * @param numberOfElements The EditText for the number of elements in the sequence.
     * @param textView The TextView where the processed data will be displayed.
     */
    fun processArithmeticData(
        firstElement: EditText,
        commonDifference: EditText,
        numberOfElements: EditText,
        textView: TextView
    ){

        val firstElementText = firstElement.text.toString()
        val commonDifferenceText = commonDifference.text.toString()
        val numberOfElementsText = numberOfElements.text.toString()

        try {

            if (firstElement.text.isEmpty() || commonDifference.text.isEmpty() || numberOfElements.text.isEmpty())
                throw NumberFormatException("Whoops! Input is empty. ")

            if (BigDecimal(numberOfElementsText) < BigDecimal.valueOf(2) || BigDecimal(numberOfElementsText) > BigDecimal.valueOf(1000)) {

                if (BigDecimal(numberOfElementsText) < BigDecimal.valueOf(2))
                    throw java.lang.NumberFormatException("Whoops! n must be greater than 2")

                if(BigDecimal(numberOfElementsText) > BigDecimal.valueOf(1000))
                    throw java.lang.NumberFormatException("Whoops! n must be less than 1000")
            }

            val nthElementSolution = DiscreteMathModel.calculateNthTermArithmetic(
                numberOfElementsText.toInt(),
                BigDecimal(firstElementText),
                BigDecimal(commonDifferenceText)
            )

            val sumOfElementsArithmetic = DiscreteMathModel.calculateSumOfTerms(
                numberOfElementsText.toInt(),
                BigDecimal(firstElementText),
                BigDecimal(commonDifferenceText)
            )
            val arithmeticSequenceList: ArrayList<BigDecimal> = DiscreteMathModel.calculateArithmeticProgression(
                numberOfElementsText.toInt(),
                BigDecimal(firstElementText),
                BigDecimal(commonDifferenceText)
            )

            "${StringUtils.formatSequenceString(arithmeticSequenceList)}\nSₙ = $sumOfElementsArithmetic\naₙ = $nthElementSolution".also { textView.text = it }
            textView.visibility = View.VISIBLE

        } catch (e: NumberFormatException) {
            numberOfElements.error = e.message
        }

    }

    /**
     * Processes user input for geometric sequence data.
     *
     * @param firstElement The EditText for the first element of the sequence.
     * @param commonRatio The EditText for the common ratio of the sequence.
     * @param numberOfElements The EditText for the number of elements in the sequence.
     * @param textView The TextView where the processed data will be displayed.
     */
    fun processGeometricData(
        firstElement: EditText,
        commonRatio: EditText,
        numberOfElements: EditText,
        textView: TextView
    ){

        val firstElementText = firstElement.text.toString()
        val commonRatioText = commonRatio.text.toString()
        val numberOfElementsText = numberOfElements.text.toString()

        try {

            if (firstElement.text.isEmpty() || commonRatio.text.isEmpty() || numberOfElements.text.isEmpty())
                throw NumberFormatException("Whoops! Input is empty. ")

           if (BigDecimal(numberOfElementsText) < BigDecimal.valueOf(2) || BigDecimal(numberOfElementsText) > BigDecimal.valueOf(1000)) {

               if (BigDecimal(numberOfElementsText) < BigDecimal.valueOf(2))
                    throw java.lang.NumberFormatException("Whoops! n must be greater than 2")

               if(BigDecimal(numberOfElementsText) > BigDecimal.valueOf(1000))
                   throw java.lang.NumberFormatException("Whoops! n must be less than 1000")

           }

            val nthElementSolution = DiscreteMathModel.calculateNthTermOfGeometricSequence(
                BigDecimal(firstElementText),
                BigDecimal(commonRatioText),
                numberOfElementsText.toInt()
            )
            val sumOfElementGeometric = DiscreteMathModel.calculateGeometricSumOfTerms(
                BigDecimal(firstElementText),
                BigDecimal(commonRatioText),
                numberOfElementsText.toInt()
            )

            val geometricSequenceList: ArrayList<BigDecimal> = DiscreteMathModel.calculateGeometricSequence(
                BigDecimal(firstElementText),
                BigDecimal(commonRatioText),
                numberOfElementsText.toInt()
            )

            "${StringUtils.formatSequenceString(geometricSequenceList)}\naₙ = $nthElementSolution\nSₙ = $sumOfElementGeometric\n ".also { textView.text = it }
            textView.visibility = View.VISIBLE

        } catch (e: NumberFormatException) {
            firstElement.error = e.message
        }
    }

    /**
     * Resets the data entered by the user.
     *
     * @param activity The activity containing the views to be reset.
     */
    fun restData(activity: AppCompatActivity) {

        val geometricFlag = activity.findViewById<RadioButton>(R.id.geometric_radio_button).isChecked
        val geometricSolutionTextView = activity.findViewById<TextView>(R.id.geometric_solution_text_view)
        val geometricNumberOfTermsEditText = activity.findViewById<EditText>(R.id.input_n)
        val geometricDifferenceEditText = activity.findViewById<EditText>(R.id.input_r)
        val geometricFirstElementEditText = activity.findViewById<EditText>(R.id.input_an)

        val arithmeticFlag = activity.findViewById<RadioButton>(R.id.arithmetic_radio_button).isChecked
        val arithmeticSolutionTextView = activity.findViewById<TextView>(R.id.arithmetic_solution_text_view)
        val arithmeticNumberOfTermsEditText = activity.findViewById<EditText>(R.id.input_x)
        val arithmeticDifferenceEditText = activity.findViewById<EditText>(R.id.input_pop_mean)
        val arithmeticFirstTermEditText = activity.findViewById<EditText>(R.id.input_std)

        if (arithmeticFlag) {
            arithmeticSolutionTextView.text = ""
            arithmeticSolutionTextView.visibility = View.GONE
            arithmeticNumberOfTermsEditText.text.clear()
            arithmeticDifferenceEditText.text.clear()
            arithmeticFirstTermEditText.text.clear()

        } else if (geometricFlag) {
            geometricSolutionTextView.text = ""
            geometricSolutionTextView.visibility = View.GONE
            geometricNumberOfTermsEditText.text.clear()
            geometricDifferenceEditText.text.clear()
            geometricFirstElementEditText.text.clear()
        }
    }
}