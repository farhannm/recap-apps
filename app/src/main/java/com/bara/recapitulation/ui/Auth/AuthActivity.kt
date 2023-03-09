package com.bara.recapitulation.ui.Auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bara.recapitulation.AdminActivity
import com.bara.recapitulation.UserActivity
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.databinding.ActivityAuthBinding
import com.bara.recapitulation.util.Pref
import com.bara.recapitulation.util.TokenManager
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()
    lateinit var binding: ActivityAuthBinding
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            showToast("Tekan sekali lagi untuk keluar")
        }

        backPressedTime = System.currentTimeMillis()
    }

    override fun onStart() {
        super.onStart()
        if (Pref.getDataLogin(this)) {
            if (Pref.getDataAs(this).equals("admin")) {
                pushActivity(AdminActivity::class.java)
                finish()
            } else {
                pushActivity(UserActivity::class.java)
                finish()
            }
        }
    }

    private fun setData(){
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){

        if (binding.inputEmail.isEmpty() || binding.inputPassword.isEmpty()) return

        val body = AuthRequest(
            binding.inputEmail.text.toString(),
            binding.inputPassword.text.toString()
        )

        viewModel.login(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    Pref.setToken(this, it.data?.api_token)
                    showToast("Selamat datang " + it.data?.nama)

                    if (it.data?.id_role == 1){
                        Pref.setDataLogin(this@AuthActivity, true)
                        Pref.setDataAs(this@AuthActivity, "admin")
                        startActivity(Intent(this@AuthActivity, AdminActivity::class.java))
                        finish()
                    } else if (it.data?.id_role == 2) {
                        Pref.setDataLogin(this@AuthActivity, true)
                        Pref.setDataAs(this@AuthActivity, "user")
                        startActivity(Intent(this@AuthActivity, UserActivity::class.java))
                        finish()
                    } else {
                        showToast("Pengguna tidak ditemukan.")
                    }
                }
                State.FAILED -> {
                    binding.inputEmail.requestFocus()
                    viewModel.dialogFailed(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                }
            }
        }
    }

}
