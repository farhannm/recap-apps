package com.bara.recapitulation.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object Pref {
    private val DATA_LOGIN = "status_login"
    private val DATA_AS = "as"
    private val TOKEN = "token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setDataAs(context: Context, data: String?) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(DATA_AS, data)
        editor.apply()
    }

    fun getDataAs(context: Context): String? {
        return getSharedPreferences(context).getString(DATA_AS, "")
    }

    fun setDataLogin(context: Context, status: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean(DATA_LOGIN, status)
        editor.apply()
    }

    fun getDataLogin(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(DATA_LOGIN, false)
    }

    fun setToken(context: Context, token: String?){
        val editor = getSharedPreferences(context).edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context) : String? {
        return getSharedPreferences(context).getString(TOKEN, "")
    }

    fun clearData(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(DATA_AS)
        editor.remove(DATA_LOGIN)
        editor.apply()
    }
}