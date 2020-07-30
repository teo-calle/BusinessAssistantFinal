package com.teo.businessassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Window
import java.util.*
import kotlin.concurrent.timerTask


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        val timer= Timer()
        timer.schedule(timerTask {
            goToLoginActivity()
        }, 1000
        )
    }
    private fun goToLoginActivity( ){
        val intent =Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }



}