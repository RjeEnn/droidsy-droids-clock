package com.doc.droidysdroidsclock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import top.defaults.colorpicker.ColorPickerPopup

class CustomiseIndividualActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customise_individual)

        /*val textView: TextView = findViewById(R.id.text34);

        val setColourBtn: Button = findViewById(R.id.set_colour)
        setColourBtn.setOnClickListener {
            val obj = object: ColorPickerPopup.ColorPickerObserver{
                override fun onColor(color: Int, fromUser: Boolean) {
                    setColourBtn.backgroundTintList = getColorStateList(color);

                }

                override fun onColorPicked(color: Int) {
                    setColourBtn.backgroundTintList = getColorStateList(color)
                }
            }
            val r = ColorPickerPopup.Builder(this)
                .initialColor(255)
                .enableBrightness(true)
                .enableAlpha(true)
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(it, obj);
            /*Intent(this, CustomiseActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0);
            }*/
        }*/
    }
}