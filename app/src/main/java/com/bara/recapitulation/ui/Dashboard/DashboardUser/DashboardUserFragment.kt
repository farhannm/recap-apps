package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan.CreatePekerjaanUserActivity
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.empty_list_recap_admin.*

class DashboardUserFragment : Fragment() {
    private val viewModel: DashboardUserViewModel by viewModel()
    private var _binding: FragmentUserDashboardBinding? = null
    private val binding get() = _binding!!
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupAdapter()
        mainButton()
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun setupAdapter(){
        binding.rvDetailPekerjaan.adapter = adapterDetailPekerjaan

        binding.rvDetailPekerjaan.toGone()
        binding.shimmerContainer.toVisible()
        binding.shimmerContainer.startShimmer()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                binding.shimmerContainer.stopShimmer()
                binding.shimmerContainer.toGone()
                binding.rvDetailPekerjaan.toVisible()
            }
        }, 1500)
    }

    private fun mainButton() {
        binding.btnAddTask.setOnClickListener {
            startActivity(Intent(context, CreatePekerjaanUserActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val user = Pref.getUser()

        if (user != null) {
            binding.txtUsername.text = user.nama
        }

        viewModel.listDetailPekerjaan.observe(viewLifecycleOwner, Observer{
            adapterDetailPekerjaan.addItems(it)
        })

        viewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.formatDateTime.text = it
        })
    }

    @SuppressLint("SetTextI18n")
    private fun getData(){
        viewModel.getPekerjaanMonth().observe(viewLifecycleOwner){
            when(it.state){
                State.LOADING -> {
                }
                State.SUCCESS -> {
                    val value = it.data ?: isNull()

                    if (value.isNull()) {
                        loge("Data is empty")
                    } else {
                        binding.apply {
                            txtPeriode.text = it.data?.start + " - " + it.data?.end
                            txtToleransi.text = it.data?.jam_toleransi
                            txtJamker.text = it.data?.total_jam
//                            txtTotalJam.text = it.data?.
                        }
                    }
                }
                State.FAILED -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}