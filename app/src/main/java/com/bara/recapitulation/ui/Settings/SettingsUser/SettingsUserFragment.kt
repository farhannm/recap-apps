package com.bara.recapitulation.ui.Settings.SettingsUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bara.recapitulation.databinding.FragmentUserSettingsBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Bantuan.BantuanActivity
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.bara.recapitulation.ui.Settings.SettingsUser.Tentang.TentangActivity

class SettingsUserFragment : Fragment() {

    private var _binding: FragmentUserSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserSettingsBinding.inflate(inflater, container, false)
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

        binding.btnHelp.setOnClickListener {
            startActivity(Intent(activity, BantuanActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}