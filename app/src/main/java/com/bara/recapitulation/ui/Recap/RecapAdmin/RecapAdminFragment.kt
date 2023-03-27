package com.bara.recapitulation.ui.Recap.RecapAdmin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bara.recapitulation.databinding.FragmentAdminRecapBinding
import com.bara.recapitulation.ui.Dashboard.adapter.PekerjaanAdapter
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.CreateRecapActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecapAdminFragment : Fragment() {
    private val viewModel: RecapAdminViewModel by viewModel()
    private var _binding: FragmentAdminRecapBinding? = null
    private val binding get() = _binding!!
    private val adapterPekerjaan = PekerjaanAdapter()

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

        setData()
        mainButton()
    }

    private fun setData() {
        binding.rvRecapPekerjaan.adapter = adapterPekerjaan

        viewModel.listPekerjaan.observe(viewLifecycleOwner, Observer{
            adapterPekerjaan.addItems(it)
        })
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