package com.bara.recapitulation.ui.Settings.SettingsAdmin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bara.recapitulation.databinding.FragmentAdminSettingsBinding
import com.bara.recapitulation.ui.Settings.SettingsAdmin.Bantuan.BantuanActivity
import com.bara.recapitulation.ui.Settings.SettingsAdmin.Profile.ProfileActivity
import com.bara.recapitulation.ui.Settings.SettingsAdmin.Tentang.TentangActivity

class SettingsAdminFragment : Fragment() {

    private var _binding: FragmentAdminSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentView()
    }

    private fun intentView() {
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

        binding.btnAbout.setOnClickListener {
            startActivity(Intent(activity, TentangActivity::class.java))
        }

//        binding.btnHelp.setOnClickListener {
//            startActivity(Intent(activity, BantuanActivity::class.java))
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}