package com.bara.recapitulation.ui.Recap.RecapAdmin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bara.recapitulation.databinding.FragmentAdminRecapBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanActivity
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.CreateRecapActivity

class RecapAdminFragment : Fragment() {

    private var _binding: FragmentAdminRecapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminRecapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentView()
    }

    private fun intentView() {
        binding.btnCreateRecap.setOnClickListener {
            startActivity(Intent(context, CreateRecapActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}