package com.bara.recapitulation.ui.Settings.SettingsUser.Profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.load
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.databinding.ActivityUserProfileBinding
import com.bara.recapitulation.ui.Settings.SettingsUser.Profile.ChangePass.ChangePasswordActivity
import com.bara.recapitulation.util.Pref
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_admin_profile.*
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

        setData()
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
                profileJabatan.text = user.jabatan

                val imageProfile = user.image

                if (imageProfile != null) {
                    profileImage.load(imageProfile) {
                        crossfade(true)
                        crossfade(1000)
                    }
                } else {
                    profileImage.load(R.drawable.ilustration_deals)
                }
            }
        }

//        viewModel.getCurrentUser().observe(this, Observer{
//            when(it.state) {
//                State.LOADING -> {
//                }
//                State.SUCCESS -> {
//                    val user = it.data ?: isNull()
//
//                    binding.apply {
//                        profileName.text = it.data?.nama
//                        profileEmail.text = it.data?.email
//                        profileJabatan.text = it.data?.jabatan
//
//                        Picasso.get().load(it.data?.image).into(binding.profileImage)
//                    }
//                }
//                State.FAILED -> {
//
//                }
//            }
//        })
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

    private fun uploadUser(){
        val file = fileImage.toMultipartBody()

        viewModel.uploadUser(file).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
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