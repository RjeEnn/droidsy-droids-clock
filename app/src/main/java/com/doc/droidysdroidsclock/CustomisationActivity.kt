package com.doc.droidysdroidsclock

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class CustomisationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FocusActivity", "created")
        setContentView(R.layout.activity_customise)
    }
}