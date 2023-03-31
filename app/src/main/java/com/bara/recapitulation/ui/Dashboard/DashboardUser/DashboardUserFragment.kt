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
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import com.bara.recapitulation.util.Pref
import com.bara.recapitulation.util.getIdPekerjaan
import com.inyongtisto.myhelper.extension.*
import kotlinx.android.synthetic.main.activity_welcome.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.empty_list_recap_admin.*
import kotlinx.android.synthetic.main.empty_list_state.*

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

        setData()
        getData()
        mainButton()
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun mainButton() {

        binding.btnAddTask.setOnClickListener {
//                val data = viewModel.getPekerjaanMonth().observe(viewLifecycleOwner){
//                    it.data?.id.toString()
//                }.toString()
            val data = binding.idPekerjaan.text

//                println("Tes Broww : $data")

            val intent = Intent(requireContext(), CreatePekerjaanUserActivity::class.java)
            intent.putExtra("id_pekerjaan", "$data")
            startActivity(intent)

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
    private fun getData() {
        viewModel.getPekerjaanMonth().observe(viewLifecycleOwner) {
            when (it.state) {
                LOADING -> {
                }
                SUCCESS -> {
                    val value = it.data ?: isNull()

                    if (value.isNull()) {
                        loge("Data is empty")
                    } else {
                        binding.apply {
                            println("Tes " + it.data)
                            txtPeriode.text = "${it.data?.start} - ${it.data?.end}"
                            txtToleransi.text = "${it.data?.jam_toleransi} jam"
                            txtJamker.text = "${it.data?.total_jam} jam"
                            idPekerjaan.text =  "${it.data?.id}"
                        }

                        val idPekerjaan = it.data?.id
                        println("Logggg $idPekerjaan")
                    }
                }
                FAILED -> {
                }
            }
        }

        viewModel.getUserCurrentMonth().observe(viewLifecycleOwner) {
            when (it.state) {
                LOADING -> {
                }
                SUCCESS -> {
                    val value = it.data ?: isNull()

                    if (value.isNull()) {
                        loge("Data is empty")
                    } else {
                        binding.txtTotalJam.text = "${it.data?.jam_kerja} jam"
                    }
                }
                FAILED -> {
                }
            }
        }

        viewModel.getUserTodayTask().observe(viewLifecycleOwner) {
            when (it.state) {
                LOADING -> {
                    binding.detailPekerjaanPlaceholder.toGone()
                    binding.shimmerContainer.toVisible()
                    binding.shimmerContainer.startShimmer()
                }
                SUCCESS -> {
                    emptyStateLayout.toGone()
                    val user = it.data ?: isNull()

                    if (user.isNull()) {
                        emptyStateLayout.toVisible()
                    } else {
                        binding.shimmerContainer.stopShimmer()
                        binding.shimmerContainer.toGone()
                        binding.detailPekerjaanPlaceholder.toVisible()

                        binding.apply {
                            txtJudulTask.text = it.data?.nama_pekerjaan
                            txtTipePekerjaan.text = it.data?.tipe
                            txtLamaJam.text = "${it.data?.jam_kerja} jam"
                        }
                    }
                }
                FAILED -> {
                    emptyStateLayout.toVisible()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}