package com.bara.recapitulation.ui.CustomDialog

import android.app.Activity
import android.app.AlertDialog
import com.bara.recapitulation.R
import com.bara.recapitulation.ui.Auth.AuthActivity
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.pushActivity
import kotlinx.android.synthetic.main.dialog_logout.view.*
import kotlinx.android.synthetic.main.dialog_logout.view.btnBatal
import kotlinx.android.synthetic.main.dialog_profile_image.view.*

class CustomDialog(val mActivity: Activity) {
    private lateinit var dialog: AlertDialog


    fun dialogSuccess(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogFailed(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_failed, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogLoading(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_loading, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogLogout(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_logout, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.btnBatal.setOnClickListener(){
            dialog.dismiss()
        }

        dialogView.btnKeluar.setOnClickListener {
            Pref.setDataLogin(mActivity, false)
            Pref.setDataAs(mActivity, "")
            mActivity.pushActivity(AuthActivity::class.java)
            mActivity.finish()
        }
    }

    fun dialogProfileImage(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_profile_image, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.btnBatal.setOnClickListener(){
            dialog.dismiss()
        }

        dialogView.uploadImageProfile.setOnClickListener {
        }

        dialogView.btnSimpanGambar.setOnClickListener {

        }
    }

    fun dialogDismiss(){
        dialog.dismiss()
    }

}