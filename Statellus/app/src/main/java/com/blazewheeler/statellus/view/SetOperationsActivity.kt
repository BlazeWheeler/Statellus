package com.blazewheeler.statellus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.model.StatisticsModel
import com.blazewheeler.statellus.utils.GradientTextUtil
import com.blazewheeler.statellus.viewmodel.SetOperationsViewModel


class SetOperationsActivity : AppCompatActivity() {


    private val viewModel: SetOperationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_operations_activity)

        initializeViews()

        findViewById<Button>(R.id.calc_button).setOnClickListener {
            errorHandling()
        }

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
        findViewById<TextView>(R.id.cartesian_product).text = getString(R.string.cartesian_product)


        /* Set activity title */
        val activityTitle = resources.getString(R.string.statistics_title)
        val activityTitleTextView = findViewById<TextView>(R.id.activity_title)
        activityTitleTextView.text = activityTitle
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5")

        /* Set activity title */
        val activityDesc = resources.getString(R.string.set_operations_title)
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
        // Check if input A is invalid
        if ( !viewModel.isValidInput(findViewById<EditText>(R.id.set_b_input).text.toString()))
            getString(R.string.invalid_input_message)

        // Check if input B is invalid
        if ( !viewModel.isValidInput(findViewById<EditText>(R.id.set_b_input).text.toString()))
            getString(R.string.invalid_input_message)

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

            val cartesianProductFinalSolution = viewModel.formatCartesianProduct(setA,setB)


            "A = {${viewModel.formatSetView(findViewById<EditText>(R.id.set_a_input).text.toString())}}".also { findViewById<TextView>(R.id.set_a).text = it }
            "B = {${viewModel.formatSetView(findViewById<EditText>(R.id.set_b_input).text.toString())}}".also { findViewById<TextView>(R.id.set_b).text = it }
            "A ∪ B = {$unionFinalSolution}".also { findViewById<TextView>(R.id.set_union).text = it }
            "A ∩ B = {$intersectionFinalSolution}".also { findViewById<TextView>(R.id.set_intersection).text = it }
            "A x B = {$cartesianProductFinalSolution}".also { findViewById<TextView>(R.id.cartesian_product).text = it }

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
        findViewById<TextView>(R.id.cartesian_product).text = getString(R.string.cartesian_product)


    }
}