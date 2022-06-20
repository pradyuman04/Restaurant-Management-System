package com.example.live_table.Activity.SplashScreenActivity.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.live_table.Activity.HomeScreenActivity.View.Home_Screen
import com.example.live_table.Utils.DBHelper
import com.example.live_table.databinding.SplashScreenBinding

class Splash_Screen : AppCompatActivity() {

    lateinit var binding: SplashScreenBinding
    var i = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this, Home_Screen::class.java)
            startActivity(intent)
            finish()
        }, 1500)

        var db=DBHelper(this)
        if(!db.checkData()) {


            while (i <= 12) {

                var dbHelper2 = DBHelper(this)
                dbHelper2.insertTableData("$i", "0")
                i++

            }
        }

    }
}