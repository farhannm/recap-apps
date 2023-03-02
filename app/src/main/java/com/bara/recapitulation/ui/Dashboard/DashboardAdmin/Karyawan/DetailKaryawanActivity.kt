package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityDetailKaryawanBinding

class DetailKaryawanActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailKaryawanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}