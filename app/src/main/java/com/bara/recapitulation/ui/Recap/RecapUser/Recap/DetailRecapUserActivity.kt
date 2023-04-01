package com.bara.recapitulation.ui.Recap.RecapUser.Recap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityDetailRecapUserBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserViewModel
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.inyongtisto.myhelper.extension.*
import kotlinx.android.synthetic.main.activity_detail_recap_user.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecapUserActivity : AppCompatActivity() {
    private val viewModel: DetailRecapUserViewModel by viewModel()
    lateinit var binding: ActivityDetailRecapUserBinding
    private val pekerjaan by extra<Pekerjaan>()
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        setData()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setData() {
        binding.rvUserTask.adapter = adapterDetailPekerjaan

//        viewModel.listUser.observe(this, Observer{
//            adapterUser.addItems(it)
//        })
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val idPk = pekerjaan?.id

        viewModel.getSelectedPk(idPk).observe(this){
            when(it.state) {
                State.LOADING -> {
                }
                State.SUCCESS -> {
                    val value = it.data?: isNull()

                    if (value.isNull()) {
                        showToast("Data is null")
                    } else {
                        binding.apply {
                            txtSelectedBulan.text = it.data?.bulan
                            txtPeriode.text = "${it.data?.start} - ${it.data?.end}"
                            txtJamker.text = "${it.data?.total_jam} jam"
                        }
                    }
                }
                State.FAILED -> {

                }
            }
        }

        viewModel.getSelectedTotalJam(idPk).observe(this){
            when(it.state) {
                State.LOADING -> {
                }
                State.SUCCESS -> {
                    val value = it.data?: isNull()

                    if (value.isNull()) {
                        showToast("Data is null")
                    } else {
                        binding.txtTotalJamker.text = it.data?.jam_kerja
                    }
                }
                State.FAILED -> {
                }
            }
        }

        viewModel.getUserTaskByMonth(idPk).observe(this){
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
                        emptyStateLayout.toGone()
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
        binding.recapDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}