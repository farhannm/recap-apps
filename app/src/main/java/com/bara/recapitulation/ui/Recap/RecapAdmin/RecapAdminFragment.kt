package com.bara.recapitulation.ui.Recap.RecapAdmin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.FragmentAdminRecapBinding
import com.bara.recapitulation.ui.Dashboard.adapter.PekerjaanAdminAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.CreateRecapActivity
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toVisible
import kotlinx.android.synthetic.main.activity_karyawan.*
import kotlinx.android.synthetic.main.empty_list_recap_admin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecapAdminFragment : Fragment() {
    private val viewModel: RecapAdminViewModel by viewModel()
    private var _binding: FragmentAdminRecapBinding? = null
    private val binding get() = _binding!!
    private val adapterPekerjaan = PekerjaanAdminAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminRecapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setData()
        mainButton()
    }

    private fun setData() {
        binding.rvRecapPekerjaan.adapter = adapterPekerjaan

//        viewModel.listPekerjaan.observe(viewLifecycleOwner, Observer{
//            adapterPekerjaan.addItems(it)
//        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun getData(){
        viewModel.getPekerjaan().observe(viewLifecycleOwner){
            when(it.state){
                State.LOADING -> {
                    binding.rvRecapPekerjaan.toGone()
                    binding.shimmerContainer.toVisible()
                    binding.shimmerContainer.startShimmer()
                }
                State.SUCCESS -> {
                    emptyStateRecapAdminLayout.toGone()
                    val data = it.data ?: emptyList()

                    if (data.isEmpty()){
                        emptyStateRecapAdminLayout.toVisible()
                    } else {
                        binding.shimmerContainer.stopShimmer()
                        binding.shimmerContainer.toGone()
                        binding.rvRecapPekerjaan.toVisible()

                        adapterPekerjaan.addItems(data)
                    }
                }
                State.FAILED -> {
                    emptyStateRecapAdminLayout.toVisible()

                }
            }
        }
    }

    private fun mainButton() {
        binding.btnCreateRecap.setOnClickListener {
            startActivity(Intent(context, CreateRecapActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}