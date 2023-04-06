package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityDetailRecapBinding
import com.bara.recapitulation.ui.Dashboard.adapter.UserAdminAdapter
import com.inyongtisto.myhelper.extension.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRecapAdminActivity : AppCompatActivity() {
    private val viewModel: DetailRecapAdminViewModel by viewModel()
    lateinit var binding: ActivityDetailRecapBinding
    private val pekerjaan by extra<Pekerjaan>()
    private val user by extra<User>()
    private val adapterUser = UserAdminAdapter()

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

    private fun mainButton() {

        binding.recapDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
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
                            txtPeriode.text = "${it.data?.mulai} - ${it.data?.berakhir}"
                            txtJamker.text = "${it.data?.total_jam} jam"
                            txtToleransi.text = "${it.data?.jam_toleransi} jam"
                            txtIdPk.text = it.data?.id.toString()
                        }
                    }
                }
                State.FAILED -> {

                }
            }
        }

        viewModel.getUser(idPk).observe(this){
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
}