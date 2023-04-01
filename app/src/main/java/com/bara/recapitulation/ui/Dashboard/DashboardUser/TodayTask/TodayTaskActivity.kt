package com.bara.recapitulation.ui.Dashboard.DashboardUser.TodayTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityKaryawanBinding
import com.bara.recapitulation.databinding.ActivityTodayTaskBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanViewModel
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.ui.Dashboard.adapter.UserAdapter
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toVisible
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodayTaskActivity : AppCompatActivity() {
    private val viewModel: TodayTaskViewModel by viewModel()
    lateinit var binding: ActivityTodayTaskBinding
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setData()
        mainButton()
    }

    private fun setData() {
        binding.rvDetailPekerjaan.adapter = adapterDetailPekerjaan

//        viewModel.listUser.observe(this, Observer{
//            adapterUser.addItems(it)
//        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun getData(){
        viewModel.getUserTodayTask().observe(this){
            when(it.state){
                State.LOADING -> {
                    emptyStateLayout.toVisible()
                }
                State.SUCCESS -> {
                    emptyStateLayout.toGone()
                    val user = it.data ?: emptyList()

                    if (user.isEmpty()){
                        emptyStateLayout.toVisible()
                    } else {
                        binding.shimmerContainer.stopShimmer()
                        binding.shimmerContainer.toGone()
                        binding.rvDetailPekerjaan.toVisible()

                        adapterDetailPekerjaan.addItems(user)
                    }
                }
                State.FAILED -> {
                    emptyStateLayout.toVisible()
                }
            }
        }
    }

    private fun mainButton() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}