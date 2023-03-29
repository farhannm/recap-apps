package com.bara.recapitulation.ui.Recap.RecapUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.FragmentUserRecapBinding
import com.bara.recapitulation.ui.Dashboard.adapter.PekerjaanAdapter
import com.bara.recapitulation.ui.Dashboard.adapter.PekerjaanAdminAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminViewModel
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toVisible
import kotlinx.android.synthetic.main.empty_list_recap_admin.*
import kotlinx.android.synthetic.main.empty_list_state.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecapUserFragment : Fragment() {
    private val viewModel: RecapUserViewModel by viewModel()
    private var _binding: FragmentUserRecapBinding? = null
    private val binding get() = _binding!!
    private val adapterPekerjaan = PekerjaanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserRecapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setData()
    }

    private fun setData(){
        binding.rvPekerjaanUser.adapter = adapterPekerjaan

        viewModel.getDate.observe(viewLifecycleOwner, Observer {
            binding.txtThisMonth.text = it
        })

    }

    @Suppress("UNCHECKED_CAST")
    private fun getData(){
        viewModel.getPekerjaan().observe(viewLifecycleOwner){
            when(it.state){
                State.LOADING -> {
                    binding.rvPekerjaanUser.toGone()
                    binding.shimmerContainer.toVisible()
                    binding.shimmerContainer.startShimmer()
                }
                State.SUCCESS -> {
                    emptyStateLayout.toGone()
                    val data = it.data ?: emptyList()

                    if (data.isEmpty()){
                        emptyStateLayout.toVisible()
                    } else {
                        binding.shimmerContainer.stopShimmer()
                        binding.shimmerContainer.toGone()
                        binding.rvPekerjaanUser.toVisible()

                        adapterPekerjaan.addItems(data)
                    }
                }
                State.FAILED -> {
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