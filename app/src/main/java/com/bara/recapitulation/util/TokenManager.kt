package com.bara.recapitulation.util

import android.content.Context
import android.content.SharedPreferences
import com.bara.recapitulation.util.Constant.PREFS_TOKEN_FILE
import com.bara.recapitulation.util.Constant.USER_TOKEN

class TokenManager(var context: Context) {
//    private var prefs: SharedPreferences =
//        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPref"

    var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor = prefs.edit()

    fun setToken(token: String){
        editor.putString("token", token)
        editor.commit()
    }

    fun getToken() : String? {
        return prefs.getString("token","")
    }

//    fun saveToken(token: String) {
//        val editor = prefs.edit()
//        editor.putString(USER_TOKEN, token)
//        editor.apply()
//    }
//
//    fun getToken(): String? {
//        return prefs.getString(USER_TOKEN, null)
//    }
}