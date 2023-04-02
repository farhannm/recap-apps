package com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityKaryawanBinding
import com.bara.recapitulation.ui.Dashboard.adapter.UserAdapter
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toVisible
import kotlinx.android.synthetic.main.activity_karyawan.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class KaryawanActivity : AppCompatActivity() {
    private val viewModel: KaryawanViewModel by viewModel()
    lateinit var binding: ActivityKaryawanBinding
    private val adapterUser = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        setData()
        mainButton()
    }

    private fun setData() {
        binding.rvUser.adapter = adapterUser

//        viewModel.listUser.observe(this, Observer{
//            adapterUser.addItems(it)
//        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun getData(){
        viewModel.getUser().observe(this){
            when(it.state){
                State.LOADING -> {
                    binding.rvUser.toGone()
                    binding.shimmerContainer.toVisible()
                    binding.shimmerContainer.startShimmer()
                }
                State.SUCCESS -> {
                    emptyStateLayout.toGone()
                    val user = it.data ?: emptyList()

                    if (user.isEmpty()){
                        emptyStateLayout.toVisible()
                    } else {
                        binding.shimmerContainer.stopShimmer()
                        binding.shimmerContainer.toGone()
                        binding.rvUser.toVisible()

                        adapterUser.addItems(user)
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