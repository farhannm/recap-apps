package com.bara.recapitulation.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.response.*
import com.chibatching.kotpref.KotprefModel
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel


object Pref : KotprefModel(){
    private val DATA_LOGIN = "status_login"
    private val DATA_AS = "as"
    private val TOKEN = "token"

    var isLogin by booleanPref(false)
    var user by stringPref()
    val data by stringPref()
    var pekerjaan by stringPref()
    var detailPk by stringPref()
    var token by stringPref("token")
    var jumlah_karyawan by stringPref("0")

    fun setUserPk(data: PkResponse?){
        user = data.toJson()
    }

    fun getUserPk() : Pekerjaan? {
        if (user.isEmpty()) return null
        return user.toModel(Pekerjaan::class.java)
    }

    fun setUserDetailPk(data: DetailPkResponse?){
        detailPk = data.toJson()
    }

    fun setUser(data: UserResponse?){
        user = data.toJson()
    }

    fun getUser() : User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }

    fun setPekerjaan(data: Pekerjaan?){
        pekerjaan = data.toJson()
    }

    fun getPekerjaan() : Pekerjaan? {
        if (data.isEmpty()) return null
        return data.toModel(Pekerjaan::class.java)
    }

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

fun getUserId() = Pref.getUser()?.id
fun getPekerjaanId() = Pref.getPekerjaan()?.id