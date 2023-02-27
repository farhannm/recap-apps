package com.bara.recapitulation.ui.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.AdminActivity
import com.bara.recapitulation.UserActivity
import com.bara.recapitulation.Welcome
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.databinding.ActivityAuthBinding
import com.bara.recapitulation.ui.CustomDialog.CustomDialog
import com.bara.recapitulation.util.SharedPref
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()

    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    override fun onStart() {
        super.onStart()
//        if (SharedPref.isLogin) {
//            pushActivity(MainActivity::class.java)
//        }
    }

    private fun setData(){
        binding.welcomeDest.setOnClickListener {
            pushActivity(Welcome::class.java)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun dialogSuccess(){
        val success = CustomDialog(this)
        success.dialogSuccess()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                success.dialogDismiss()
            }

        }, 3000)
    }

    private fun dialogFailed(){
        val failed = CustomDialog(this)
        failed.dialogFailed()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                failed.dialogDismiss()
            }

        }, 2500)
    }

    private fun dialogLoading(){
        val loading = CustomDialog(this)
        loading.dialogLoading()
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.dialogDismiss()
            }

        }, 1000)
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
                    SharedPref.isLogin = true
                    showToast("Selamat datang " + it.data?.name)

                    if (it.data?.roles == "admin"){
                        pushActivity(AdminActivity::class.java)
                        finish()
                    } else if (it.data?.roles == "user") {
                        pushActivity(UserActivity::class.java)
                        finish()
                    } else {
                        showToast("Pengguna tidak ditemukan.")
                    }
                }
                State.FAILED -> {
                    binding.inputEmail.requestFocus()
                    dialogFailed()
                }
                State.LOADING -> {
                    dialogLoading()
                }
            }
        }
    }

    private fun sessionRole(){
        val email: String = binding.inputEmail.text.toString()
        val password: String = binding.inputEmail.text.toString()


    }

}
