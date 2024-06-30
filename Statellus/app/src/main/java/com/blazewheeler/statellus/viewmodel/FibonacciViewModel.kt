package com.blazewheeler.statellus.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.DiscreteMathModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.utils.StringUtils
import ru.noties.jlatexmath.JLatexMathView

import java.math.BigInteger

/**
 * ViewModel class for handling Fibonacci sequence operations.
 */
class FibonacciViewModel : ViewModel() {

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

        val activityDesc = resources.getString(R.string.fibonacci_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardLabelOne = resources.getString(R.string.single_title)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        val cardLabelTwo = resources.getString(R.string.sequence_title)
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
     * Sets default LaTeX formulas for single Fibonacci number and Fibonacci sequence.
     *
     * @param jLatexMathView1 The JLatexMathView for displaying the formula for single Fibonacci number.
     * @param jLatexMathView2 The JLatexMathView for displaying the formula for Fibonacci sequence.
     */
    fun setDefaultFormulas(jLatexMathView1: JLatexMathView, jLatexMathView2: JLatexMathView){
        jLatexMathView1.setLatex("F_n = F_{n-1} + F_{n-2}")
        jLatexMathView2.setLatex("F_n = F_{n-1} + F_{n-2}")
    }

    /**
     * Handles error checking and processing for single Fibonacci number.
     *
     * @param inputData The EditText for input data.
     * @param jLatexMathView1 The JLatexMathView for displaying the formula and result.
     * @param textView The TextView where the result will be displayed.
     */
    fun singleNumErrorHandling(inputData: EditText, jLatexMathView1: JLatexMathView, textView: TextView){

        val inputDataText = inputData.text.toString()

        try {
            val inputNumber = Integer.valueOf(StringUtils.decimalFormatter(inputDataText))
            if (inputNumber > 499){
                inputData.text.clear()
                throw NumberFormatException("Input number exceeds 499")
            }

            val finalSolution: String = StringUtils.formatWithCommas(DiscreteMathModel.calculateFibonacci(inputNumber))
            jLatexMathView1.setLatex("F_{\${$inputDataText}} = F_{n-1} + F_{n-2} \\\\\\\\ F_{$inputDataText} =")

            textView.text = finalSolution
            textView.visibility = View.VISIBLE

        } catch (e: NumberFormatException) {
            inputData.error = "Please Enter Value less than 500"
        }
    }

    /**
     * Handles error checking and processing for Fibonacci sequence.
     *
     * @param inputData1 The EditText for input data (start number).
     * @param inputData2 The EditText for input data (end number).
     * @param jLatexMathView2 The JLatexMathView for displaying the formula and result.
     * @param textView The TextView where the result will be displayed.
     */
    fun sequenceNumErrorHandling(inputData1: EditText, inputData2: EditText, jLatexMathView2: JLatexMathView, textView: TextView){

        val inputDataText1 = inputData1.text.toString()
        val inputDataText2 = inputData2.text.toString()

        try {
            if (inputData1.text.isEmpty() || inputData2.text.isEmpty()){
                throw NumberFormatException("Whoops! Input is empty. ")
            }
            val inputNumber1 = Integer.valueOf(inputDataText1)
            val inputNumber2 = Integer.valueOf(inputDataText2)
            val difference = inputNumber2 - inputNumber1

           if (inputNumber1 >= inputNumber2){
                inputData1.text.clear()
                inputData2.text.clear()
                throw NumberFormatException("\uD835\uDC391 > \uD835\uDC392 ")
            }
            else if (inputNumber1 > 199 || inputNumber2 > 199) {
                inputData1.text.clear()
                inputData2.text.clear()
                throw NumberFormatException("Input number exceeds 199")
            } else if (difference > 50 ){
                inputData1.text.clear()
                inputData2.text.clear()
                throw NumberFormatException("Input Difference exceeds 50")
            } else {
                val list1: ArrayList<BigInteger> = DiscreteMathModel.calculateFibonacciSequence(inputNumber1.toInt(), inputNumber2.toInt())
                jLatexMathView2.setLatex("F_{n} = F_{$inputDataText1} \\ to \\ F_{$inputDataText2} \\\\\\\\ F_{n} = ")

                val stringWithoutBrackets = list1.toString().replace("[\\[\\]]".toRegex(), "")
                textView.text = stringWithoutBrackets
                textView.visibility = View.VISIBLE
            }

        } catch (e: NumberFormatException) {
            inputData1.error = e.message
        }
    }
}