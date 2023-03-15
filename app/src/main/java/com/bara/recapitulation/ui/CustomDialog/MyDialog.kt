package com.bara.recapitulation.ui.CustomDialog

import android.app.Activity
import android.app.AlertDialog
import com.bara.recapitulation.R
import com.bara.recapitulation.ui.Auth.AuthActivity
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.pushActivity
import kotlinx.android.synthetic.main.dialog_logout.view.*
import kotlinx.android.synthetic.main.dialog_logout.view.btnBatal
import kotlinx.android.synthetic.main.dialog_validate_delete_task.view.*
import kotlinx.android.synthetic.main.dialog_validate_delete_user.view.*

class MyDialog(val mActivity: Activity) {
    private lateinit var dialog: AlertDialog

    //Success
    fun dialogSuccessGeneric(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_generic, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogSuccessProfile(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_profile, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogSuccessCreateTask(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_create_task, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogSuccessCreateUser(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_create_user, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogSuccessEditUser(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_edit_user, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogSuccessEditTask(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_success_edit_task, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    //Failed
    fun dialogFailedAuth(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_failed, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogFailedGeneric(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_failed_generic, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun dialogFailedChangePass(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_failed_change_pass, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    //Loading
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

    //Delete
    fun dialogConfirmDeleteUser(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_validate_delete_user, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.btnBatalDeleteUser.setOnClickListener(){
            dialog.dismiss()
        }

        dialogView.btnDeleteUser.setOnClickListener {

        }
    }

    fun dialogConfirmDeleteTask(){
        val inflater  = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_validate_delete_task, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog = builder.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.btnBatalDeleteTask.setOnClickListener(){
            dialog.dismiss()
        }

        dialogView.btnDeleteTask.setOnClickListener {

        }
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


    fun dialogDismiss(){
        dialog.dismiss()
    }

}