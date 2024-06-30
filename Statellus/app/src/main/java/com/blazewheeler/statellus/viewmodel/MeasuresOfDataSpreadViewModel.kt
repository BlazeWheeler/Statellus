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
class MeasuresOfDataSpreadViewModel : ViewModel() {

    // Spacing issue with latex s and s^2
    // Latex formulas for different statistical measures
    private val rangeLatex = "\\text{Range} = \\text{Max} - \\text{Min} = "
    private val iqrLatex = "\\text{IQR} = Q_3 - Q_1 = "
    private val varianceLatex =  "\\[ s^2 = \\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})^2}{n - 1} = \\]"
    private val standardDeviationLatex = "\\[ s = \\sqrt{\\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})^2}{n-1}} = \\]"
    private val madLatex = "\\text{MAD} = \\frac{1}{n} \\sum_{i=1}^{n} |x_i - \\bar{x}| = "
    private val cvLatex = "CV = \\frac{s}{\\bar{x}} \\times 100 = "
    private val popVarianceLatex = "\\[\\sigma^2 = \\frac{\\sum_{i=1}^{n} (x_i - \\mu)^2}{N}\\] = "
    private val popStandardDeviationLatex = "\\sigma = \\sqrt{\\frac{\\sum_{i=1}^{N} (x_i - \\mu)^2}{N}} = "
    private val popRangeLatex = "\\text{Range} = \\text{Max} - \\text{Min} = " // Population Range
    private val popIqrLatex =  "\\text{IQR} = Q_3 - Q_1 = " // Population IQR
    private val popMadLatex = "\\text{MAD} = \\frac{1}{N} \\sum_{i=1}^{N} |x_i - \\mu| = " // Population MAD
    private val popCvLatex = "\\text{CV} = \\left( \\frac{\\sigma}{\\mu} \\right) \\times 100\\% = " // Population CV

    // LiveData for holding latex formulas
    private val _latexFormulas: MutableLiveData<List<String>> = MutableLiveData()
    val latexFormulas: LiveData<List<String>> = _latexFormulas

    init {
        // Initialize default formulas
        provideDefaultFormula()
    }

    /**
     * Provides default latex formulas for different statistical measures.
     */
    fun provideDefaultFormula() {
        val defaultFormulas = listOf(
            "\\[s = \\sqrt{\\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})^2}{n-1}} = N/A\\]", // Sample Standard Deviation
            "\\[ s^2 = \\frac{\\sum_{i=1}^{n} (x_i - \\bar{x})^2}{n - 1} = N/A\\]", // Sample Variance
            "\\text{Range} = \\text{Max} - \\text{Min} = N/A", // Sample Range
            "\\text{IQR} = Q_3 - Q_1 = N/A", // Sample IQR
            "\\text{MAD} = \\frac{1}{n} \\sum_{i=1}^{n} |x_i - \\bar{x}| = N/A", // Sample MAD
            "\\text{CV} = \\frac{s}{\\bar{x}} \\times 100 = N/A", // Sample CV
            "\\sigma = \\sqrt{\\frac{\\sum_{i=1}^{N} (x_i - \\mu)^2}{N}} = N/A", // Population Variance
            "\\[\\sigma^2 = \\frac{\\sum_{i=1}^{n} (x_i - \\mu)^2}{n}\\] = N/A  ", // Population Standard Deviation
            "\\text{Range} = \\text{Max} - \\text{Min} = N/A", // Population Range
            "\\text{IQR} = Q_3 - Q_1 = N/A", // Population IQR
            "\\text{MAD} = \\frac{1}{N} \\sum_{i=1}^{N} |x_i - \\mu| = N/A", // Population MAD
            "\\text{CV} = \\left( \\frac{\\sigma}{\\mu} \\right) \\times 100\\% = N/A" // Population CV
        )
        _latexFormulas.value = defaultFormulas
    }

    /**
     * Processes the input data provided by the user, calculates statistical measures, and updates the corresponding views.
     *
     * @param inputData EditText containing the input data provided by the user.
     * @param varianceView JLatexMathView for displaying the variance.
     * @param standardDeviationView JLatexMathView for displaying the standard deviation.
     * @param rangeView JLatexMathView for displaying the range.
     * @param iqrView JLatexMathView for displaying the interquartile range.
     * @param madView JLatexMathView for displaying the mean absolute deviation.
     * @param cvView JLatexMathView for displaying the coefficient of variation.
     */
    fun processData(
        inputData: EditText, standardDeviationView: JLatexMathView, varianceView: JLatexMathView,  rangeView: JLatexMathView,
        iqrView: JLatexMathView, madView: JLatexMathView, cvView: JLatexMathView, isPopulationData: Boolean)
    {
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
                if (entries.size < 4) {
                    inputData.error = "Whoops! Please enter a data set with at least 4 elements."
                } else if (entries.size > 50) {
                    inputData.error = "Whoops! Please keep data sets below size of 50."
                } else {

                    if (!isPopulationData){
                        val variance = StatisticsModel.calculateVariance(entries, false)
                        val varianceString = StringUtils.addSolutionToLatex(varianceLatex,StringUtils.bigZeroDecimalToInt(variance))
                        varianceView.setLatex(varianceString)

                        val standardDeviation = StatisticsModel.calculateStandardDeviation(entries,false)
                        val standardDeviationString = StringUtils.addSolutionToLatex(standardDeviationLatex, StringUtils.bigZeroDecimalToInt(standardDeviation))
                        standardDeviationView.setLatex(standardDeviationString)

                        val range = StatisticsModel.calcRange(entries)
                        val rangeString = StringUtils.addSolutionToLatex(rangeLatex, StringUtils.bigZeroDecimalToInt(range))
                        rangeView.setLatex(rangeString)

                        val iqr = StatisticsModel.calcInterQuartileRange(entries)
                        val iqrString = StringUtils.addSolutionToLatex(iqrLatex, StringUtils.bigZeroDecimalToInt(iqr))
                        iqrView.setLatex(iqrString)

                        val mad = StatisticsModel.calculateMeanAbsoluteDeviation(entries)
                        val madString = StringUtils.addSolutionToLatex(madLatex, StringUtils.bigZeroDecimalToInt(mad))
                        madView.setLatex(madString)

                        val cv = StatisticsModel.calculateCoefficientOfVariation(entries, false)
                        val cvString = StringUtils.addSolutionToLatex(cvLatex, StringUtils.bigZeroDecimalToInt(cv))
                        cvView.setLatex(cvString)

                    } else {
                        val variance = StatisticsModel.calculateVariance(entries, true)
                        val varianceString = StringUtils.addSolutionToLatex(popVarianceLatex,StringUtils.bigZeroDecimalToInt(variance))
                        varianceView.setLatex(varianceString)

                        val standardDeviation = StatisticsModel.calculateStandardDeviation(entries,true)
                        val standardDeviationString = StringUtils.addSolutionToLatex(popStandardDeviationLatex, StringUtils.bigZeroDecimalToInt(standardDeviation))
                        standardDeviationView.setLatex(standardDeviationString)
                        val range = StatisticsModel.calcRange(entries)
                        val rangeString = StringUtils.addSolutionToLatex(popRangeLatex, StringUtils.bigZeroDecimalToInt(range))
                        rangeView.setLatex(rangeString)

                        val iqr = StatisticsModel.calcInterQuartileRange(entries)
                        val iqrString = StringUtils.addSolutionToLatex(popIqrLatex, StringUtils.bigZeroDecimalToInt(iqr))
                        iqrView.setLatex(iqrString)

                        val mad = StatisticsModel.calculateMeanAbsoluteDeviation(entries)
                        val madString = StringUtils.addSolutionToLatex(popMadLatex, StringUtils.bigZeroDecimalToInt(mad))
                        madView.setLatex(madString)

                        val cv = StatisticsModel.calculateCoefficientOfVariation(entries,true)
                        val cvString = StringUtils.addSolutionToLatex(popCvLatex, StringUtils.bigZeroDecimalToInt(cv))
                        cvView.setLatex(cvString)
                    }
                }
            }
        } catch (e: NumberFormatException) {
            inputData.error = "Enter only comma-separated numeric values."
        } catch (e: IllegalArgumentException){
            inputData.error = e.message ?: "Number greater than 11 digits cannot be entered."
        }
    }

    /**
     * Processes the input data provided by the user and updates the corresponding views with population statistics.
     *
     * @param inputData EditText containing the input data provided by the user.
     * @param popVarianceView JLatexMathView for displaying the population variance.
     * @param popStandardDeviationView JLatexMathView for displaying the population standard deviation.
     * @param popRangeView JLatexMathView for displaying the population range.
     * @param popIqrView JLatexMathView for displaying the population interquartile range.
     * @param popMadView JLatexMathView for displaying the population mean absolute deviation.
     * @param popCvView JLatexMathView for displaying the population coefficient of variation.
     */
    fun processPopulationData(
        inputData: EditText, popStandardDeviationView: JLatexMathView, popVarianceView: JLatexMathView,  popRangeView: JLatexMathView,
        popIqrView: JLatexMathView, popMadView: JLatexMathView, popCvView: JLatexMathView, isPopulationData: Boolean
    ) {
        processData(inputData, popStandardDeviationView, popVarianceView, popRangeView, popIqrView, popMadView, popCvView, isPopulationData)
    }

    /**
     * Initialize the UI elements with default values and styles.
     *
     * @param activity The AppCompatActivity instance where the UI elements are located.
     */
    fun initializeViews(activity: AppCompatActivity) {

        // Provide default latex formulas
        provideDefaultFormula()

        // Access resources
        val resources = activity.resources

        // Set activity title
        val activityTitle = resources.getString(R.string.statistics_title)
        val activityTitleTextView = activity.findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        // Set activity description
        val activityDesc = resources.getString(R.string.measure_of_spread_title)
        val activityDescTextView = activity.findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        // Set card one label
        val cardLabelOne = resources.getString(R.string.sample_title)
        val cardOneTextView = activity.findViewById<TextView>(R.id.card_one_title)
        cardOneTextView.text = cardLabelOne
        GradientTextUtil.applyGradientText(cardOneTextView, cardLabelOne,"#EC3CAB", "#0B40C5" )

        // Set card two label
        val cardLabelTwo = resources.getString(R.string.population_title)
        val cardTwoTextView = activity.findViewById<TextView>(R.id.card_two_title)
        cardTwoTextView.text = cardLabelTwo
        GradientTextUtil.applyGradientText(cardTwoTextView, cardLabelTwo,"#EC3CAB", "#0B40C5" )

        // Set card three label
        val cardThreeLabel = resources.getString(R.string.enter_data_title)
        val enterDataTextView = activity.findViewById<TextView>(R.id.card_three_title)
        enterDataTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, cardThreeLabel, "#EC3CAB", "#0B40C5")
    }
}
