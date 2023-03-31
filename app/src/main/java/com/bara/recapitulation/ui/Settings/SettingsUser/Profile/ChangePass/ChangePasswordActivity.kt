package com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.databinding.ActivityChangePasswordBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileActivity
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ProfileViewModel
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.int
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import kotlinx.android.synthetic.main.activity_change_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    private fun intentView() {
        binding.btnChangePass.setOnClickListener {

            if (binding.inputPasswordLama.isEmpty()){
                binding.inputPasswordLama.error = "Password Lama tidak boleh kosong"
                binding.inputPasswordLama.requestFocus()
            }else if (binding.inputPasswordBaru.isEmpty()){
                binding.inputPasswordBaru.error = "Password Baru tidak boleh kosong"
                binding.inputPasswordBaru.requestFocus()
            }else if (binding.inputKonfirmasiPassword.isEmpty()){
                binding.inputKonfirmasiPassword.error = "Konfirmasi Password baru anda"
                binding.inputKonfirmasiPassword.requestFocus()
            }else if (binding.inputKonfirmasiPassword.text.toString() != binding.inputPasswordBaru.text.toString()) {
                binding.inputKonfirmasiPassword.error = "Password tidak sesuai."
                binding.inputKonfirmasiPassword.requestFocus()
            }else {
                changePass()
            }
        }

        binding.profileDest.setOnClickListener {
            onSupportNavigateUp()
        }

    }

    private fun changePass() {

//        if (binding.inputPasswordLama.isEmpty()) return
//        if (binding.inputPasswordBaru.isEmpty()) return
//        if (binding.inputKonfirmasiPassword.isEmpty()) return

        val idUser = Pref.getUser()?.id
        val body = UserRequest(
            idUser.int(),
            password = binding.inputPasswordBaru.text.toString(),
        )

        viewModel.updateUser(body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    viewModel.dialogSuccess(this)
                    showToast("Berhasil merubah data " + it.data?.nama)
                    onBackPressed()
                }
                State.FAILED -> {
                    viewModel.dialogSuccess(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}