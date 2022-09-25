package com.example.live_table.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.live_table.R
import com.example.live_table.databinding.ActivityMenuBinding
import com.example.live_table.databinding.FragmentMenuBinding

class menuActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImage.setOnClickListener {

            onBackPressed()

        }
    }
}