package com.blazewheeler.statellus.view

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class TruthTableGeneratorView : AppCompatActivity() {
    private lateinit var truthTableInput: TextView
    private lateinit var webViewLatex: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truth_table_generator_view)

        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        truthTableInput = findViewById(R.id.truth_table_input)
        webViewLatex = findViewById(R.id.webview_latex)
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val buttons = listOf(
            Pair(findViewById<Button>(R.id.button_p), "P"),
            Pair(findViewById<Button>(R.id.button_q), "Q"),
            Pair(findViewById<Button>(R.id.button_r), "R"),
            Pair(findViewById<Button>(R.id.button_s), "S"),
            Pair(findViewById<Button>(R.id.button_t), "T"),
            Pair(findViewById<Button>(R.id.button_and), "∧"),
            Pair(findViewById<Button>(R.id.button_or), "∨"),
            Pair(findViewById<Button>(R.id.button_not), "¬"),
            Pair(findViewById<Button>(R.id.button_implication), "→"),
            Pair(findViewById<Button>(R.id.button_biconditional), "↔"),
            Pair(findViewById<Button>(R.id.button_xor), "⊕"),
            Pair(findViewById<Button>(R.id.button_nand), "⊼"),
            Pair(findViewById<Button>(R.id.button_parenthesis_open), "("),
            Pair(findViewById<Button>(R.id.button_parenthesis_close), ")"),
            Pair(findViewById<Button>(R.id.button_backspace), "⌫"),
            Pair(findViewById<Button>(R.id.button_clear), "C"),
            Pair(findViewById<Button>(R.id.button_calculate), "=")
        )

        for ((button, text) in buttons) {
            button.setOnClickListener { onButtonClick(text) }
        }
    }

    private fun onButtonClick(text: String) {
        when (text) {
            "C" -> {
                truthTableInput.text = ""
                webViewLatex.loadData("", "text/html", "UTF-8")
            }
            "⌫" -> {
                val currentText = truthTableInput.text.toString()
                if (currentText.isNotEmpty()) {
                    truthTableInput.text = currentText.dropLast(1)
                }
            }
            "=" -> validateAndCalculateTruthTable(truthTableInput.text.toString())
            else -> truthTableInput.append(text)
        }
    }

    private fun validateAndCalculateTruthTable(expression: String) {
        if (isValidExpression(expression)) {
            calculateTruthTable(expression)
        } else {
            truthTableInput.text = "Error: Invalid expression"
            Toast.makeText(this, "Invalid expression. Please correct it.", Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidExpression(expression: String): Boolean {
        // Add your validation logic here
        // For simplicity, let's check if the expression contains at least one variable and one operator
        val operators = listOf("∧", "∨", "¬", "→", "↔", "⊕", "⊼")
        val variables = listOf("P", "Q", "R", "S", "T")
        val hasVariable = variables.any { expression.contains(it) }
        val hasOperator = operators.any { expression.contains(it) }
        return hasVariable && hasOperator
    }

    private fun calculateTruthTable(expression: String) {
        val python = Python.getInstance()
        val pythonModule = python.getModule("truth_table_logic")

        try {
            val variables = extractVariables(expression)
            val formattedExpression = formatExpression(expression)

            Log.i("TruthTable", "Variables: $variables")
            Log.i("TruthTable", "Expressions: $formattedExpression")

            val result = pythonModule.callAttr("generate_truth_table", variables.joinToString(","), formattedExpression.joinToString(",")).toString()

            // Replace unsupported LaTeX commands for JLatexMathView
            var cleanedResult = result.replace("\\ensuremath{>}", ">")

            // Replace textual operators with symbols
            cleanedResult = cleanedResult.replace("=>", "→")
                .replace("xor", "⊕")
                .replace("nand", "⊼")
                .replace("and", "∧")
                .replace("or", "∨")
                .replace("not", "¬")
                .replace("=", "↔")

            // Print the result to the log for debugging purposes
            Log.i("TruthTable", "Result: $cleanedResult")

            // Load HTML content in WebView
            webViewLatex.loadDataWithBaseURL(null, cleanedResult, "text/html", "UTF-8", null)

            // Optionally update the UI with the result
            Toast.makeText(this, "Generated Truth Table: See Logcat for details.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            truthTableInput.text = "Error: ${e.message}"
            Toast.makeText(this, "Error generating truth table: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("TruthTableError", "Failed to generate truth table: ${e.message}")
        }
    }

    private fun extractVariables(expression: String): List<String> {
        val variables = mutableSetOf<String>()
        for (char in expression) {
            if (char.isLetter() && char in 'P'..'T') {
                variables.add(char.toString())
            }
        }
        return variables.toList()
    }

    private fun formatExpression(expression: String): List<String> {
        val spacedExpression = expression.replace("∧", " ∧ ")
            .replace("∨", " ∨ ")
            .replace("¬", " ¬ ")
            .replace("→", " → ")
            .replace("↔", " ↔ ")
            .replace("⊕", " ⊕ ")
            .replace("⊼", " ⊼ ")

        return listOf(spacedExpression.replace("∧", "and")
            .replace("∨", "or")
            .replace("¬", "not")
            .replace("→", "=>")
            .replace("↔", "=")
            .replace("⊕", "xor")
            .replace("⊼", "nand"))
    }
}
