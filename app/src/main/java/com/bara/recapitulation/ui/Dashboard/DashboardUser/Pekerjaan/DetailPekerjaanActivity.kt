package com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityDetailPekerjaanBinding

class DetailPekerjaanActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailPekerjaanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPekerjaanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}