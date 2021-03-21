package com.felipheallef.tipcalculator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_main)
        val billEditText = findViewById<EditText>(R.id.edit_text)
        val calculateButton = findViewById<Button>(R.id.button)
        val tipPercentageSlider = findViewById<Slider>(R.id.slider)
        val tipPercentageLabel = findViewById<TextView>(R.id.label_tip_percentage)

        var tipPercentage = 0F
        var tip: Double

        textView.visibility = View.GONE

        tipPercentageSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
                tipPercentageLabel.text = getString(R.string.label_tip_percentage) + " (" + slider.value.toInt() + "%)"
            }
        })

        tipPercentageSlider.addOnChangeListener { _, value, _ ->
            // Responds to when slider's value is changed
            tipPercentage = value / 100F
        }

        calculateButton.setOnClickListener {

            if (billEditText.text.isNotEmpty()) {
                tip = billEditText.text.toString().toDouble() * tipPercentage
                textView.text = getString(R.string.text_tip_result).format(tip)
                textView.visibility = View.VISIBLE
            } else {
                Snackbar.make(it, getString(R.string.alert_fill_out_all_data), Snackbar.LENGTH_LONG).show()
            }

        }

    }
}