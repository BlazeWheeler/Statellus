package com.blazewheeler.statellus.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.blazewheeler.statellus.viewmodel.ZscoreViewModel
import ru.noties.jlatexmath.JLatexMathView

/**
 * Activity for the Z-Score Calculator functionality.
 */
class ZscoreView : AppCompatActivity() {

    private val viewModel: ZscoreViewModel by viewModels()

    private lateinit var zScoreFormula: JLatexMathView
    private lateinit var zScoreSolutionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zscore_view)

        // Initialize views
        zScoreFormula = findViewById(R.id.j_latex_math_view_1)
        zScoreSolutionTextView = findViewById(R.id.z_score_solution)
        zScoreSolutionTextView.visibility = View.VISIBLE

        // Initialize ViewModel and set default formulas
        viewModel.initializeViews(this)
        viewModel.setDefaultFormulas(findViewById(R.id.z_score_solution), zScoreFormula, this)

        // Set listener for calculation button
        findViewById<Button>(R.id.calc_button).setOnClickListener {
            viewModel.processZscoreData(
                findViewById(R.id.input_x),
                findViewById(R.id.input_pop_mean),
                findViewById(R.id.input_std),
                zScoreSolutionTextView,
                this
            )
        }

        // Set listener for reset button
        findViewById<Button>(R.id.reset_button).setOnClickListener {
            viewModel.setDefaultFormulas(
                findViewById(R.id.z_score_solution),
                jLatexMathView1 = findViewById(R.id.j_latex_math_view_1),
                this
            )
            viewModel.restData(this)
        }
    }
}