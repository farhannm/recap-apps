package com.bara.recapitulation.ui.Dashboard.DashboardAdmin

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
import com.bara.recapitulation.databinding.FragmentAdminDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanActivity
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanActivity
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel
import com.bara.recapitulation.util.Pref
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

    private fun setData() {
        val user = Pref.getUser()
        if (user != null) {
            binding.apply {
                "${user.jumlah_karyawan} working".also { txtJumlahKaryawan.text = it }
            }
        } else {
            binding.txtJumlahKaryawan.text = "0"
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