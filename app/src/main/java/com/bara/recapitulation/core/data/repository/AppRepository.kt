package com.bara.recapitulation.core.data.repository

import com.bara.recapitulation.core.data.source.local.LocalDataSource
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.RemoteDataSource
import com.bara.recapitulation.core.data.source.remote.network.Resource
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.util.SharedPref
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: AuthRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    SharedPref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    val role = body?.data?.roles
                    SharedPref.setUser(user)
                    emit(Resource.success(user))
                    logs("Berhasil : " + body.toString())
                } else {
                    emit(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
                    logs("Error : " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.failed(e.message?: "Terjadi kesalahan!", null))
            logs("Error : " + e.message)
        }
    }

}