package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityUserProfileBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass.ChangePasswordActivity
import com.bara.recapitulation.util.Pref
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileViewModel by viewModel()
    lateinit var binding: ActivityUserProfileBinding
    private var fileImage: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    private fun mainButton(){
        binding.btnChangePass.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        binding.settingsDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLogout.setOnClickListener{
            viewModel.dialogLogout(this)
        }

        binding.uploadImageProfile.setOnClickListener {
            picImage()
        }
    }

    private fun setData(){
        val user = Pref.getUser()
        if (user != null) {

            binding.apply {
                profileName.text = user.nama
                profileEmail.text = user.email
                profileRole.text = user.status

                Picasso.get().load(user.image).into(binding.profileImage)
            }
        }
    }

    private fun picImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(3080, 3080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path!!)
                Picasso.get().load(fileImage!!).into(binding.profileImage)
                uploadUser()
            }
        }

//    private fun updateUser(){
//        val idUser = Pref.getUser()?.id
//
//        val body = UpdateUserRequest(
//            idUser.int(),
//            nama = binding.profileName.text.toString(),
//            email = binding.profileEmail.text.toString(),
//            status = binding.profileRole.text.toString()
//
//        )
//
//        viewModel.updateUser(body).observe(this) {
//
//            when (it.state) {
//                State.SUCCESS -> {
//                    viewModel.dialogSuccess(this)
//                    onBackPressed()
//                }
//                State.FAILED -> {
//                    viewModel.dialogFailed(this)
//                }
//                State.LOADING -> {
//                    viewModel.dialogLoading(this)
//                }
//            }
//        }
//    }

    private fun uploadUser(){
        val idUser = Pref.getUser()?.id
        val file = fileImage.toMultipartBody()
        val userToken = Pref.getUser()?.api_token

        viewModel.uploadUser(userToken, idUser, file).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    Pref.getToken(this)
                    setData()
                    viewModel.dialogSuccess(this)
                }
                State.FAILED -> {
                    viewModel.dialogFailed(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}