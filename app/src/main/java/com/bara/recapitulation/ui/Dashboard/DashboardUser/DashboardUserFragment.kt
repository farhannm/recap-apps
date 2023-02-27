package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import java.text.SimpleDateFormat

class DashboardUserFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardUserViewModel

    private var _binding: FragmentUserDashboardBinding? = null
    private val binding get() = _binding!!

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

        setData()
    }

    private fun setData(){
        dashboardViewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.formatDateTime.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}