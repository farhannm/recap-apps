package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityDetailRecapUserBinding

class DetailRecapAdminActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailRecapUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}