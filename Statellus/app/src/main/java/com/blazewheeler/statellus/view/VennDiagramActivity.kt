package com.blazewheeler.statellus.view

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.StatisticsModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.viewmodel.VennDiagramViewModel
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

/**
 * Activity for displaying Venn Diagram.
 */
class VennDiagramActivity : AppCompatActivity() {

    // ViewModel for Venn Diagram activity
    private val viewModel: VennDiagramViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venn_diagram)

        //Initialize Views
        val noDataIcon: ImageView = findViewById(R.id.no_data_img)
        val vennView: ImageView = findViewById(R.id.imageView)
        noDataIcon.visibility = View.VISIBLE
        initializeViews()

        // Start Python if not already started
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("plot")

        // Set OnClickListener for the calculate button
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            errorHandling()
            try {
                val bytes = module.callAttr("plot_venn", findViewById<EditText>(R.id.set_a_input).text.toString(), findViewById<EditText>(R.id.set_b_input).text.toString())
                    .toJava(ByteArray::class.java)
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                noDataIcon.visibility = View.INVISIBLE
                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
                currentFocus?.let {
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(it.windowToken, 0)
                }
                vennView.visibility = View.VISIBLE
            } catch (e: PyException) {
                vennView.visibility = View.INVISIBLE
                noDataIcon.visibility = View.VISIBLE
            }
        }

        // Set OnClickListener for the reset button
        findViewById<Button>(R.id.reset_button).setOnClickListener {
            resetViews()
        }
    }

    /**
     * Initializes the views with default values and formatting.
     */
    private fun initializeViews() {
        findViewById<TextView>(R.id.set_a).text = getString(R.string.venn_set_a_text)
        findViewById<TextView>(R.id.set_b).text = getString(R.string.venn_set_b_text)
        findViewById<TextView>(R.id.set_union).text = getString(R.string.venn_set_union_text)
        findViewById<TextView>(R.id.set_intersection).text = getString(R.string.venn_set_intersection_text)

        /* Set activity title */
        val activityTitle = resources.getString(R.string.data_visualization_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        /* Set activity title */
        val activityDesc = resources.getString(R.string.venn_diagram_title)
        val activityDescTextView = findViewById<TextView>(R.id.activity_desc)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5")

        /* Set card Three label */
        val enterDataTextView = findViewById<TextView>(R.id.card_two_title)
        enterDataTextView.text = getString(R.string.enter_data_title)
        GradientTextUtil.applyGradientText(enterDataTextView, getString(R.string.enter_data_title), "#EC3CAB", "#0B40C5")

        /* Set card Three label */
        val setOperationTitle = findViewById<TextView>(R.id.set_ops_title)
        setOperationTitle.text = getString(R.string.set_operations_title)
        GradientTextUtil.applyGradientText(setOperationTitle, getString(R.string.set_operations_title), "#EC3CAB", "#0B40C5")
    }

    /**
     * Handles input errors and displays error messages.
     */
    private fun errorHandling() {
        // Check if input A is empty or invalid
        if (findViewById<EditText>(R.id.set_a_input).text.toString().isEmpty() || !viewModel.isValidInput(findViewById<EditText>(R.id.set_b_input).text.toString())) {
            findViewById<EditText>(R.id.set_a_input).error = if (findViewById<EditText>(R.id.set_a_input).text.toString().isEmpty()) {
                getString(R.string.empty_input_error)
            } else {
                getString(R.string.invalid_input_message)
            }
        }

        // Check if input B is empty or invalid
        if (findViewById<EditText>(R.id.set_b_input).text.toString().isEmpty() || !viewModel.isValidInput(findViewById<EditText>(R.id.set_b_input).text.toString())) {
            findViewById<EditText>(R.id.set_b_input).error = if (findViewById<EditText>(R.id.set_b_input).text.toString().isEmpty()) {
                getString(R.string.empty_input_error)
            } else {
                getString(R.string.invalid_input_message)
            }
        }

        initTextViews()
    }

    /**
     * Initializes the TextViews with calculated solutions if inputs are valid.
     */
    private fun initTextViews() {
        if (viewModel.isValidInput(findViewById<EditText>(R.id.set_a_input).text.toString()) && viewModel.isValidInput(findViewById<EditText>(R.id.set_b_input).text.toString())) {
            val setA = viewModel.convertInputToFloatList(findViewById<EditText>(R.id.set_a_input).text.toString())
            val setB = viewModel.convertInputToFloatList(findViewById<EditText>(R.id.set_b_input).text.toString())
            val unionFinalSolutionList = StatisticsModel.setUnion(setA, setB)
            val unionFinalSolution = viewModel.formatSolution(unionFinalSolutionList)
            val intersectionFinalSolutionList = StatisticsModel.setIntersection(setA, setB)
            val intersectionFinalSolution = viewModel.formatSolution(intersectionFinalSolutionList)
            "A ∪ B = {$unionFinalSolution}".also { findViewById<TextView>(R.id.set_union).text = it }
            "A ∩ B = {$intersectionFinalSolution}".also { findViewById<TextView>(R.id.set_intersection).text = it }
            "A = {${viewModel.formatSetView(findViewById<EditText>(R.id.set_a_input).text.toString())}}".also { findViewById<TextView>(R.id.set_a).text = it }
            "B = {${viewModel.formatSetView(findViewById<EditText>(R.id.set_b_input).text.toString())}}".also { findViewById<TextView>(R.id.set_b).text = it }
        }
    }

    /**
     * Resets all views to their initial state.
     */
    private fun resetViews() {
        findViewById<EditText>(R.id.set_a_input).text.clear()
        findViewById<EditText>(R.id.set_b_input).text.clear()
        findViewById<TextView>(R.id.set_a).text = getString(R.string.venn_set_a_text)
        findViewById<TextView>(R.id.set_b).text = getString(R.string.venn_set_b_text)
        findViewById<TextView>(R.id.set_union).text = getString(R.string.venn_set_union_text)
        findViewById<TextView>(R.id.set_intersection).text = getString(R.string.venn_set_intersection_text)

        // Restore the alpha value of the image drawable
        findViewById<ImageView>(R.id.imageView).visibility = View.INVISIBLE
        findViewById<ImageView>(R.id.no_data_img).visibility = View.VISIBLE
    }
}
