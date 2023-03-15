package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan.CreatePekerjaanUserActivity
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.util.Pref

class DashboardUserFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardUserViewModel
    private var _binding: FragmentUserDashboardBinding? = null
    private val binding get() = _binding!!
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardUserViewModel::class.java)
        _binding = FragmentUserDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        clickListener()
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun setupAdapter(){
        binding.rvDetailPekerjaan.adapter = adapterDetailPekerjaan
    }

    private fun clickListener() {
        binding.btnAddTask.setOnClickListener {
            startActivity(Intent(context, CreatePekerjaanUserActivity::class.java))
        }
    }

    private fun setData() {
        val user = Pref.getUser()
        if (user != null) {
            binding.txtUsername.text = user.nama
            dashboardViewModel.getDate.observe(viewLifecycleOwner, Observer {
                binding.formatDateTime.text = it
            })

            dashboardViewModel.listDetailPekerjaan.observe(requireActivity(), Observer{
                adapterDetailPekerjaan.addItems(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}