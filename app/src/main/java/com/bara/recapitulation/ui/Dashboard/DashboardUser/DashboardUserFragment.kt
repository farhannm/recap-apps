package com.bara.recapitulation.ui.Dashboard.DashboardUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.network.State.*
import com.bara.recapitulation.databinding.FragmentUserDashboardBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan.CreatePekerjaanUserActivity
import com.bara.recapitulation.ui.Dashboard.DashboardUser.TodayTask.TodayTaskActivity
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.empty_list_recap_admin.*
import kotlinx.android.synthetic.main.empty_list_state.*

class DashboardUserFragment : Fragment() {
    private val viewModel: DashboardUserViewModel by viewModel()
    private var _binding: FragmentUserDashboardBinding? = null
    private val binding get() = _binding!!

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

        setData()
        mainButton()
    }

    override fun onResume() {
        getData()
        setData()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnAddTask.setOnClickListener {
//            startActivity(Intent(context, CreatePekerjaanUserActivity::class.java))

            var idPk = binding.txtIdPekerjaan.text

            val intent = Intent(requireContext(), CreatePekerjaanUserActivity::class.java)
            intent.putExtra("id_pekerjaan", idPk)
            startActivity(intent)
        }

        binding.btnTaskToday.setOnClickListener {
            startActivity(Intent(context, TodayTaskActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        val user = Pref.getUser()

        if (user != null) {
            binding.txtUsername.text = user.nama
        }

        viewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.formatDateTime.text = it
        })
    }
    @Suppress("UNCHECKED_CAST")
    @SuppressLint("SetTextI18n")
    private fun getData(){
        viewModel.getPekerjaanMonth().observe(viewLifecycleOwner){
            when(it.state){
                LOADING -> {

                }
                SUCCESS -> {
                    val value = it.data

                    if (value.isNull()) {
                        loge("Data is empty")
                    } else {
                        binding.apply {
                            txtPeriode.text = "${it.data?.start} - ${it.data?.end}"
                            txtToleransi.text = "${it.data?.jam_toleransi} jam"
                            txtJamker.text = "${it.data?.total_jam} jam"
                            txtIdPekerjaan.text = it.data?.id.toString()
                        }
                    }
                }
                FAILED -> {
                }
            }
        }

        viewModel.getUserCurrentMonth().observe(viewLifecycleOwner){
            when(it.state) {
                LOADING -> {
                    binding.txtTotalJam.setText("0 jam")
                }
                SUCCESS -> {
                    val value = it.data

                    if (value.isNull()) {
                        loge("Tidak ada data")
                    } else {
                        binding.txtTotalJam.text = "${it.data?.jam_kerja} jam"
                    }
                }
                FAILED -> {
                    binding.txtTotalJam.setText("0 jam")
                }
            }
        }

        viewModel.getUserCountTodayTask().observe(viewLifecycleOwner){
            when(it.state) {
                State.LOADING -> { binding.txtTodayTask.setText("Tidak ada task hari ini") }
                State.SUCCESS -> {
                    val data = it.data

                    if (data.isNull()) {
                        loge("Tidak ada data")
                    } else {
                        binding.txtTodayTask.text = "${it.data?.hari_ini} task hari ini"
                    }
                }
                State.FAILED -> { binding.txtTodayTask.setText("Tidak ada task hari ini") }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}