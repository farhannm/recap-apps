package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.ActivityKaryawanBinding
import com.bara.recapitulation.ui.Dashboard.adapter.UserAdapter

class KaryawanActivity : AppCompatActivity() {
    private lateinit var viewModel: KaryawanViewModel
    lateinit var binding: ActivityKaryawanBinding
    private val adapterUser = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KaryawanViewModel::class.java)
        binding = ActivityKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        mainButton()
    }

    private fun setData() {
        binding.rvUser.adapter = adapterUser

        viewModel.listUser.observe(this, Observer{
            adapterUser.addItems(it)
        })
    }

    private fun mainButton() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}