package com.bara.recapitulation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bara.recapitulation.databinding.ActivityAdminBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.DashboardAdminFragment
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserFragment
import com.bara.recapitulation.ui.Recap.RecapAdmin.RecapAdminFragment
import com.bara.recapitulation.ui.Recap.RecapUser.RecapUserFragment
import com.bara.recapitulation.ui.Settings.SettingsAdmin.SettingsAdminFragment
import com.bara.recapitulation.ui.Settings.SettingsUser.SettingsUserFragment

class AdminActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminBinding
    val fm : FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView(){
        binding.bottomNav.setOnItemSelectedListener {
            it.isChecked = true

            when(it.itemId) {
                R.id.admin_nav_dashboard -> replaceFragment(DashboardAdminFragment())
                R.id.admin_nav_recap -> replaceFragment(RecapAdminFragment())
                R.id.admin_nav_settings -> replaceFragment(SettingsAdminFragment())
            }

            true
        }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.containerFragment, fragment)

        fragmentTransacion.commit()
    }
}