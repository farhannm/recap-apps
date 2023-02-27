package com.bara.recapitulation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityWelcomeBinding
import com.bara.recapitulation.ui.Auth.AuthActivity

class Welcome : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

    }
}