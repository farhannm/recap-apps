package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityDetailRecapBinding
import com.bara.recapitulation.databinding.ActivityDetailRecapUserBinding
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.ui.Dashboard.adapter.UserAdapter
import com.bara.recapitulation.ui.Recap.RecapUser.Recap.DetailRecapUserViewModel
import com.inyongtisto.myhelper.extension.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecapAdminActivity : AppCompatActivity() {
    private val viewModel: DetailRecapAdminViewModel by viewModel()
    lateinit var binding: ActivityDetailRecapBinding
    private val pekerjaan by extra<Pekerjaan>()
    private val adapterUser = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        setData()
        getData()
    }

    private fun setData() {
        binding.rvRecapUser.adapter = adapterUser
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
                            txtToleransi.text = "${it.data?.jam_toleransi} jam"
                        }
                    }
                }
                State.FAILED -> {

                }
            }
        }

        viewModel.getUser().observe(this){
            when(it.state){
                State.LOADING -> {
                    binding.rvRecapUser.toGone()
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
                        binding.rvRecapUser.toVisible()

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
        binding.recapDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}