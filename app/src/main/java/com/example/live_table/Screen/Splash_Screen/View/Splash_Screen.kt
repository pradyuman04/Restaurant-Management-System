package com.example.live_table.Screen.Splash_Screen.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.live_table.Screen.HomeScreenActivity.View.Home_Screen
import com.example.live_table.databinding.SplashScreenBinding

class Splash_Screen : AppCompatActivity() {

    lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }, 1500)

    }
}