package com.bara.recapitulation.ui.Dashboard.DashboardAdmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentAdminDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel
import java.util.*

class DashboardAdminFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardAdminViewModel
    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardAdminViewModel::class.java)
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
        dashboardViewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.formatDateTime.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}