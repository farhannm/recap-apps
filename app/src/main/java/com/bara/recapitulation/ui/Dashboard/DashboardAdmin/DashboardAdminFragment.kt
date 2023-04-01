package com.bara.recapitulation.ui.Dashboard.DashboardAdmin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.FragmentAdminDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanActivity
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanActivity
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.isNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_welcome.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DashboardAdminFragment : Fragment() {

    private val viewModel: DashboardAdminViewModel by viewModel()
    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentView()
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun intentView() {
        binding.btnCreateKaryawan.setOnClickListener {
            startActivity(Intent(context, CreateKaryawanActivity::class.java))
        }

        binding.txtAllKaryawan.setOnClickListener {
            startActivity(Intent(context, KaryawanActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        viewModel.getCountKaryawan().observe(viewLifecycleOwner){
            when(it.state) {
                State.LOADING -> { binding.txtJumlahKaryawan.setText("Tidak ada karyawan") }
                State.SUCCESS -> {
                    val data = it.data ?: isNull()

                    if (data.isNull()) {
                        binding.txtJumlahKaryawan.setText("Tidak ada karyawan")
                    } else {
                        binding.txtJumlahKaryawan.text = "${it.data?.jumlah_karyawan} working"
                    }
                }
                State.FAILED -> { binding.txtJumlahKaryawan.setText("Tidak ada karyawan") }
            }
        }

        viewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.formatDateTime.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}