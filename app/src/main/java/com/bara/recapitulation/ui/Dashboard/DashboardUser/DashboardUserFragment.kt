package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Sms.Intents
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Task.CreateTaskActivity
import com.bara.recapitulation.util.SharedPref
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

        initData()
    }

    private fun initData() {
        val user = SharedPref.getUser()
        if (user != null) {
            binding.txtUsername.setText(user.nama)
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