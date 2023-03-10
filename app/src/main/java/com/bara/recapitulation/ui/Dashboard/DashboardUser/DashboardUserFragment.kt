package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.AdminActivity
import com.bara.recapitulation.UserActivity
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Task.CreateTaskActivity
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.pushActivity

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

        clickListener()
    }

    override fun onResume() {
        initData()
        super.onResume()
    }

    private fun clickListener() {
        binding.btnAddTask.setOnClickListener {
            startActivity(Intent(context, CreateTaskActivity::class.java))
        }
    }

    private fun initData() {
        val user = Pref.getUser()
        if (user != null) {
            binding.txtUsername.text = user.nama
            dashboardViewModel.getDate.observe(viewLifecycleOwner, Observer {
                binding.formatDateTime.text = it
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}