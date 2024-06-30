package com.blazewheeler.statellus.view

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle

import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.StatisticsModel.calculateInnerQuartile
import com.blazewheeler.statellus.model.StatisticsModel.calculateMax
import com.blazewheeler.statellus.model.StatisticsModel.calculateMedian
import com.blazewheeler.statellus.model.StatisticsModel.calculateMin
import com.blazewheeler.statellus.model.StatisticsModel.calculateThirdQuartile
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.viewmodel.BoxPlotViewModel
import com.chaquo.python.PyException
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import ru.noties.jlatexmath.JLatexMathView

/**
 * Activity class for displaying box plot visualization and statistics.
 */
class BoxPlotActivity : AppCompatActivity() {
    private lateinit var plotModule: Any
    private val viewModel: BoxPlotViewModel by viewModels()

    /**
     * Lifecycle method called when the activity is created.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_box_plot)

        val latexLabel = findViewById<JLatexMathView>(R.id.j_latex_math_view_1)
        val inputData = findViewById<EditText>(R.id.sample_input)

        initializePython()

        /* Must call after starting python */
        initializeViews(latexLabel)

        /* Set listeners for buttons */
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            generatePlotAndSummary(inputData, latexLabel)
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            restActivity(latexLabel)
        }
    }

    /**
     * Method to hide the soft keyboard.
     */
    private fun hideKeyboard() {
        currentFocus?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    /**
     * Method to display a blank plot.
     */
    private fun blankPlot() {

        /* Generate blank plot image */
        try {
            val bytes = (plotModule as PyObject).callAttr("generate_box_plot", "").toJava(ByteArray::class.java)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            hideKeyboard()

        } catch (e: PyException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method to initialize views.
     *
     * @param latexLabel The LaTeX math view.
     */
    private fun initializeViews(latexLabel: JLatexMathView) {

        /* Set LaTeX label */
        latexLabel.setLatex(viewModel.generateLatexString(null,null,null,null,null,null))

        /* Display blank plot */
        blankPlot()

        /* Set activity title */
        val activityTitle = resources.getString(R.string.data_visualization_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        /* Set activity description */
        val activityDesc = resources.getString(R.string.box_and_whisker_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityDescTextView.text = activityDesc
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        /* Set card Three label */
        val cardThreeLabel = resources.getString(R.string.enter_data_title)
        val enterDataTextView = findViewById<TextView>(R.id.card_three_title)
        enterDataTextView.setText(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, cardThreeLabel, "#EC3CAB", "#0B40C5")
    }

    /**
     * Method to calculate and display summary statistics.
     *
     * @param inputData The input data.
     * @param latexLabel The LaTeX math view.
     */
    private fun calculateAndDisplaySummary(inputData: EditText, latexLabel: JLatexMathView) {
        try {
            /* Calculate statistics */
            val min = calculateMin(viewModel.dataStringToArrayList(inputData.text.toString()))
            val q1 = calculateInnerQuartile(viewModel.dataStringToArrayList(inputData.text.toString()))
            val median = calculateMedian(viewModel.dataStringToArrayList(inputData.text.toString()))
            val q3 = calculateThirdQuartile(viewModel.dataStringToArrayList(inputData.text.toString()))
            val max = calculateMax(viewModel.dataStringToArrayList(inputData.text.toString()))
            val range = max - min

            /* Set LaTeX label with statistics */
            latexLabel.setLatex(viewModel.generateLatexString(min, q1, median, q3, max, range))
            hideKeyboard()
        } catch (e: Exception) {
            Toast.makeText(this, "Five Number Summary Format Error", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method to reset the activity.
     *
     * @param latexLabel The LaTeX math view.
     */
    private fun restActivity(latexLabel: JLatexMathView) {
        findViewById<EditText>(R.id.sample_input).text.clear()
        latexLabel.setLatex(viewModel.generateLatexString(null,null,null,null,null,null))
        blankPlot()

    }

    /**
     * Method to generate plot and summary statistics.
     *
     * @param inputData The input data.
     * @param latexLabel The LaTeX math view.
     */
    private fun generatePlotAndSummary(inputData: EditText, latexLabel: JLatexMathView) {
        try {
            /* Generate plot image */
            val bytes = (plotModule as PyObject).callAttr("generate_box_plot", inputData.text.toString())
                .toJava(ByteArray::class.java)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)

            /* Calculate and display summary statistics */
            calculateAndDisplaySummary(inputData, latexLabel)
        } catch (e: PyException) {
            inputData.error = "Invalid input. Make sure you entered only comma-separated numeric values."
        }
    }

    /**
     * Method to initialize Python environment.
     */
    private fun initializePython() {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        plotModule = py.getModule("plot")
    }

}