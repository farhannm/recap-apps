package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.databinding.ActivityCreateKaryawanBinding

class CreateKaryawanActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateKaryawanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnTambahKaryawan.setOnClickListener {

        }
    }
}