package com.blazewheeler.statellus.viewmodel

import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.StatisticsModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.utils.StringUtils



import ru.noties.jlatexmath.JLatexMathView
import java.lang.IllegalArgumentException

/**
 * ViewModel class responsible for handling business logic and UI-related data for the MeasureOfCentralTendency activity.
 */
class MeasureOfCentralTendencyViewModel : ViewModel() {

    private val meanLatex = "\\bar{x} = \\frac{1}{n}\\sum_{i=1}^{n} x_i = "
    private val medianLatex = "\\tilde{x} = x_{\\left(\\frac{n+1}{2}\\right)} = "
    private val modeLatex = "\\text{Mode} = "
    private val popMeanLatex = "\\mu = \\frac{1}{N} \\sum_{i=1}^{N} x_i ="
    private val popMedianLatex = "\\tilde{x} = x_{\\left(\\frac{N+1}{2}\\right)} ="

    private val _latexFormulas: MutableLiveData<List<String>> = MutableLiveData()
    val latexFormulas: LiveData<List<String>> = _latexFormulas

    /**
     * Processes the input data provided by the user, calculates the mean, median, and mode, and updates the corresponding views.
     *
     * @param inputData EditText containing the input data provided by the user.
     * @param meanView JLatexMathView for displaying the mean.
     * @param medianView JLatexMathView for displaying the median.
     * @param modeView JLatexMathView for displaying the mode.
     * @param populationMeanView JLatexMathView for displaying the population mean.
     * @param populationMedianView JLatexMathView for displaying the population median.
     * @param populationModeView JLatexMathView for displaying the population mode.
     */
    fun processData(
        inputData: EditText, meanView: JLatexMathView, medianView: JLatexMathView, modeView: JLatexMathView,
        populationMeanView: JLatexMathView, populationMedianView: JLatexMathView, populationModeView: JLatexMathView
    ) {
        val dataString = inputData.text.toString()
        val entries = ArrayList<Float>()

        try {
            if (TextUtils.isEmpty(dataString)) {
                inputData.error = "Enter Values."
            } else {
                val separatedValues = ArrayList(dataString.split(",").dropLastWhile { it.isEmpty() })
                for (i in separatedValues.indices) {
                    val value = separatedValues[i].toFloat()
                    if (value.toString().length > 11) {
                        throw IllegalArgumentException("Number greater than 11 digits cannot be entered.")
                    }
                    entries.add(value)
                }
                if (entries.size <= 1) {
                    throw NumberFormatException("Enter only comma-separated numeric values.")

                } else if (entries.size > 50) {
                    inputData.error = "Please keep data sets below size of 50."
                }

                else {

                    val mean = StatisticsModel.calcBigMean(entries)
                    val meanString = StringUtils.addSolutionToLatex(meanLatex, StringUtils.bigZeroDecimalToInt(mean))
                    meanView.setLatex(meanString)


                    val median = StatisticsModel.calculateBigMedian(entries)
                    val medianString = StringUtils.addSolutionToLatex(medianLatex, StringUtils.bigZeroDecimalToInt(median))
                    medianView.setLatex(medianString)

                    val modes = StatisticsModel.calculateBigMode(entries)
                    val modeString = StringUtils.addBigModesToLatex(modeLatex, modes)
                    modeView.setLatex(modeString)

                    val popMean = StatisticsModel.calcBigMean(entries)
                    val popMeanString = StringUtils.addSolutionToLatex(popMeanLatex, StringUtils.bigZeroDecimalToInt(popMean))
                    populationMeanView.setLatex(popMeanString)

                    val popMedian = StatisticsModel.calculateBigMedian(entries)
                    val popMedianString = StringUtils.addSolutionToLatex(popMedianLatex, StringUtils.bigZeroDecimalToInt(popMedian))
                    populationMedianView.setLatex(popMedianString)

                    val popModes = StatisticsModel.calculateBigMode(entries)
                    val popModeString = StringUtils.addBigModesToLatex(modeLatex, popModes)
                    populationModeView.setLatex(popModeString)


                }
            }
        } catch (e: NumberFormatException) {
            inputData.error =  e.message ?: "Enter only comma-separated numeric values."
        } catch (e: IllegalArgumentException){
            inputData.error = e.message ?: "Number greater than 11 digits cannot be entered."
        }

    }

    fun provideDefaultFormula() {
        val defaultFormulas = listOf(
            "\\bar{x} = \\frac{1}{n}\\sum_{i=1}^{n} x_i = N/A",
            "\\tilde{x} = x_{\\left(\\frac{n+1}{2}\\right)} = N/A",
            "\\text{Mode} = \\text{value that appears most frequently}",
            "\\mu = \\frac{1}{N} \\sum_{i=1}^{N} x_i = N/A",
            "\\tilde{x} = x_{\\left(\\frac{N+1}{2}\\right)} = N/A",
            "\\text{Mode} = \\text{value that appears most frequently}"
        )
        _latexFormulas.value = defaultFormulas
    }

    fun initializeViews(activity: AppCompatActivity) {

       provideDefaultFormula()

        val resources = activity.resources
        // Set titles and apply gradient text for visual styling
        val activityTitle = resources.getString(R.string.statistics_title)
        val activityTitleTextView = activity.findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        val activityDesc = resources.getString(R.string.measure_of_ct_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        val cardLabelOne = resources.getString(R.string.sample_title)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        val cardLabelTwo = resources.getString(R.string.population_title)
        val cardTwoTextView = activity.findViewById<TextView>(R.id.card_two_title)
        cardTwoTextView.text = cardLabelTwo
        GradientTextUtil.applyGradientText(cardTwoTextView, cardLabelTwo,"#EC3CAB", "#0B40C5" )

        val cardThreeLabel = resources.getString(R.string.enter_data_title)
        val enterDataTextView = activity.findViewById<TextView>(R.id.card_three_title)
        enterDataTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, cardThreeLabel, "#EC3CAB", "#0B40C5")
    }
}


