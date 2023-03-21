package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.RegisterRequest
import com.bara.recapitulation.databinding.ActivityCreateKaryawanBinding
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.isEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateKaryawanActivity : AppCompatActivity() {
    private val viewModel: CreateKaryawanViewModel by viewModel()
    lateinit var binding: ActivityCreateKaryawanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        super.onResume()
        myDropdown()
    }


    private fun myDropdown() {
        val tipe = resources.getStringArray(R.array.jabatan)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe)

        binding.inputAutoCompleteJabatan.setAdapter(arrayAdapter)
    }

    private fun mainButton() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnTambahKaryawan.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        myDropdown()

        if (binding.inputEmailKaryawan.isEmpty()
            || binding.inputPasswordKaryawan.isEmpty()
            || binding.inputNamaKaryawan.isEmpty()
            || binding.inputPhoneNumber.isEmpty()
            || binding.inputAlamat.isEmpty() ) return

        val userToken = Pref.getUser()?.api_token
        val body = RegisterRequest(
            binding.inputEmailKaryawan.text.toString(),
            binding.inputPasswordKaryawan.text.toString(),
            binding.inputNamaKaryawan.text.toString(),
            binding.inputAutoCompleteJabatan.text.toString(),
            binding.inputPhoneNumber.text.toString(),
            binding.inputAlamat.text.toString()
        )


        viewModel.createUser(userToken, body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    Pref.getToken(this)
                    viewModel.dialogSuccess(this)
                    val handler = android.os.Handler()
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }, 3000)
                }
                State.FAILED -> {
                    viewModel.dialogFailed(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                }
            }
        }
    }
}