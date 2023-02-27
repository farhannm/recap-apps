package com.bara.recapitulation.util

import com.chibatching.kotpref.KotprefModel
import com.bara.recapitulation.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object SharedPref : KotprefModel() {

    var isLogin by booleanPref(false)
    var isFirstLaunch by booleanPref(false)
    var user by stringPref()

    //data siswa
    var siswa by stringPref()
    var kelasSiswa by stringPref()
    var jurusanSiswa by stringPref()

    fun setUser(data: User?){
        user = data.toJson()
    }

    fun getUser() : User? {
        if (user.isEmpty()) return null
        return user.toModel(User::class.java)
    }

    //Data siswa
//    fun getSiswa() : Siswa? {
//
//        return siswa.toModel(Siswa::class.java)
//    }
//
//    fun getKelasSiswa() : Kelas? {
//
//        return kelasSiswa.toModel(Kelas::class.java)
//    }
//
//    fun getJurusanSiswa() : Jurusan? {
//
//        return jurusanSiswa.toModel(Jurusan::class.java)
//    }

}