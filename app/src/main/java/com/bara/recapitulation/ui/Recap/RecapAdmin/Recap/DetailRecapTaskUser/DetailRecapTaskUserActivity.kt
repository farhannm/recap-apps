package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapTaskUser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.databinding.ActivityDetailRecapTaskUserBinding
import com.bara.recapitulation.databinding.ActivityDetailTaskUserBinding
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.inyongtisto.myhelper.extension.*
import kotlinx.android.synthetic.main.empty_list_recap_admin.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailRecapTaskUserActivity : AppCompatActivity() {

    private val viewModel: DetailRecapTaskUserViewModel by viewModel()
    lateinit var binding: ActivityDetailRecapTaskUserBinding
    private val user by extra<User>()
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapTaskUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        mainButton()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun setAdapter(){
        binding.rvUserTask.adapter = adapterDetailPekerjaan
        binding.txtSelectedUser.text = user?.nama
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        println("Id pekerjaan selected ${user?.id_pekerjaan}")

        viewModel.getTaskById(user?.id, user?.id_pekerjaan).observe(this){
            when(it.state){
                State.LOADING -> {
                    emptyStateRecapAdminLayout.toVisible()
                    binding.rvUserTask.toGone()
                }
                State.SUCCESS -> {
                    emptyStateRecapAdminLayout.toGone()
                    val user = it.data ?: emptyList()

                    if (user.isEmpty()){
                        emptyStateRecapAdminLayout.toVisible()
                    } else {
                        emptyStateRecapAdminLayout.toGone()
                        adapterDetailPekerjaan.addItems(user)
                        binding.rvUserTask.toVisible()
                    }
                }
                State.FAILED -> {
                    emptyStateRecapAdminLayout.toVisible()
                }
            }
        }

        viewModel.getTotalJamByUser(user?.id, user?.id_pekerjaan).observe(this){
            when(it.state) {
                State.LOADING -> {
                    binding.txtTotalJamker.setText("0 jam")
                }
                State.SUCCESS -> {
                    if (it.data?.jam_kerja == null) {
                        binding.txtTotalJamker.setText("0 jam")
                    } else {
                        binding.txtTotalJamker.text = it.data?.jam_kerja
                    }
                }
                State.FAILED -> {
                    binding.txtTotalJamker.setText("0 jam")
                }
            }
        }


        viewModel.getSelectedPk(user?.id_pekerjaan).observe(this){
            when(it.state) {
                State.LOADING -> {
                    binding.txtPeriode.setText("...")
                }
                State.SUCCESS -> {
                    if (it.data?.start.isNull() && it.data?.end.isNull()) {
                        binding.txtPeriode.setText("...")
                    } else {
                        binding.txtPeriode.text = "${it.data?.mulai} - ${it.data?.berakhir}"
                    }
                }
                State.FAILED -> {
                    binding.txtTotalJamker.setText("...")
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