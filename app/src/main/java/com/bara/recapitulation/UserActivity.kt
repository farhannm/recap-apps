package com.bara.recapitulation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bara.recapitulation.databinding.ActivityUserBinding
import com.bara.recapitulation.ui.Dashboard.DashboardUser.DashboardUserFragment
import com.bara.recapitulation.ui.Recap.RecapUser.RecapUserFragment
import com.bara.recapitulation.ui.Settings.SettingsUser.SettingsUserFragment
import com.inyongtisto.myhelper.extension.showToast

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    val fm : FragmentManager = supportFragmentManager
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    override fun onResume() {
        intentView()
        super.onResume()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            showToast("Tekan sekali lagi untuk keluar")
        }

        backPressedTime = System.currentTimeMillis()
    }

    private fun intentView(){
        binding.bottomNav.setOnItemSelectedListener {
            it.isChecked = true

            when(it.itemId) {
                R.id.user_nav_dashboard -> replaceFragment(DashboardUserFragment())
                R.id.user_nav_recap -> replaceFragment(RecapUserFragment())
                R.id.user_nav_settings -> replaceFragment(SettingsUserFragment())
            }

            true
        }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.navHostFragment, fragment)

        fragmentTransacion.commit()
    }
}