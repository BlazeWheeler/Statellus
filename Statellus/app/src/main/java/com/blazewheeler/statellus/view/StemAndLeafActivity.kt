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
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.chaquo.python.PyException
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform


/**
 * Activity class for displaying stem-and-leaf plot visualization.
 */
class StemAndLeafActivity : AppCompatActivity() {
    private lateinit var plotModule: PyObject

    /**
     * Lifecycle method called when the activity is created.
     *
     * @param savedInstanceState The saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stem_and_leaf)

        val inputData = findViewById<EditText>(R.id.sample_input)

        /* String = "0" is dummy val to produce blank plot */
       val blankString = "0"

        initializePython()
        displayStemAndLeafPlot(blankString)

        /* Must call after starting python */
        initializeViews()

        /* Set listeners for buttons */
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            generatePlot(inputData)
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            resetActivity(blankString)
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
     * Method to display a stem-and-leaf plot.
     */
    private fun displayStemAndLeafPlot(blankString: String) {
        /* Generate plot image */
        try {
            val bytes = plotModule.callAttr("generate_stem_and_leaf", blankString)
                .toJava(ByteArray::class.java)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            hideKeyboard()
        } catch (e: PyException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method to initialize views.
     */
    private fun initializeViews() {
        /* Set activity title */
        val activityTitle = resources.getString(R.string.data_visualization_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        /* Set activity title */
        val activityDesc = resources.getString(R.string.stem_and_leaf_plot_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        /* Set card Three label */
        val enterDataTextView = findViewById<TextView>(R.id.card_three_title)
        enterDataTextView.text = getString(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, getString(R.string.enter_data_title), "#EC3CAB", "#0B40C5")
    }

    /**
     * Method to reset the activity.
     */
    private fun resetActivity(blankString: String) {
        findViewById<EditText>(R.id.sample_input).text.clear()
        displayStemAndLeafPlot(blankString)
    }

    /**
     * Method to generate stem-and-leaf plot.
     *
     * @param inputData The input data.
     */
    private fun generatePlot(inputData: EditText) {
        try {
            /* Generate plot image */
            val bytes = plotModule.callAttr("generate_stem_and_leaf", inputData.text.toString())
                .toJava(ByteArray::class.java)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
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