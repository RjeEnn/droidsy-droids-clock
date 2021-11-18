package com.doc.droidysdroidsclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.ImageButton
import android.widget.ImageView

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val by: ImageView = findViewById(R.id.imageViewBy)
        val clock: ImageView = findViewById(R.id.imageViewClock)
        val plus: ImageView = findViewById(R.id.imageViewPlus)
        val enter: ImageButton = findViewById(R.id.btn_enter)


        val clockAnim = AlphaAnimation(0f, 1f)
        val byAnim = AlphaAnimation(0f, 1f)
        clockAnim.duration = 1500
        byAnim.duration = 3500

        clock.startAnimation(clockAnim)
        plus.startAnimation(clockAnim)
        by.startAnimation(byAnim)

        enter.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(0, 0)
            }
        }
    }
}