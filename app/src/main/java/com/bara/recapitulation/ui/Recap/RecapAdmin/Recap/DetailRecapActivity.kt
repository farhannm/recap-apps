package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityDetailRecapBinding

class DetailRecapActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailRecapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView() {

    }
}