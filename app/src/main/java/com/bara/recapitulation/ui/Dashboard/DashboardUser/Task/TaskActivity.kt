package com.bara.recapitulation.ui.Dashboard.DashboardUser.Task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}