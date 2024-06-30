package com.blazewheeler.statellus.view


/*
This Activity is a test activity and still in progress please ingore for PR still a work in progress not
apart of user story 1.2
*/


import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blazewheeler.statellus.R
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class SimplePlotActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_plot)

        //TODO: Testing, Input validation, comma seperated format, Implment Reset Button

        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("plot")
        blankPlot()





        findViewById<Button>(R.id.calcButton).setOnClickListener {
            try {
                val bytes = module.callAttr("plot",
                    findViewById<EditText>(R.id.etX).text.toString(),
                    findViewById<EditText>(R.id.etY).text.toString())
                    .toJava(ByteArray::class.java)
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)

                currentFocus?.let {
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(it.windowToken, 0)
                }
            } catch (e: PyException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.resetButton).setOnClickListener {
            findViewById<EditText>(R.id.etX).text.clear()
            findViewById<EditText>(R.id.etY).text.clear()
            blankPlot()

        }
    }


    private fun blankPlot() {
        val py = Python.getInstance()
        val module = py.getModule("plot")
        try {
            val bytes = module.callAttr("plot",
                "", "").toJava(ByteArray::class.java)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)

            currentFocus?.let {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(it.windowToken, 0)
            }
        } catch (e: PyException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
}