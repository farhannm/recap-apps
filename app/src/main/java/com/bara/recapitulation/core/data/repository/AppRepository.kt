package com.bara.recapitulation.core.data.repository

import com.bara.recapitulation.core.data.source.local.LocalDataSource
import com.bara.recapitulation.core.data.source.remote.RemoteDataSource
import com.bara.recapitulation.core.data.source.remote.network.Resource
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.core.data.source.remote.request.UserRequest
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: AuthRequest) = channelFlow {
        send(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Pref.setUserAuth(user)
                    send(Resource.success(user))
                    logs("Berhasil : " + body.toString())
                } else {
                    send(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
                    logs("Error : " + "keterangan error")
                }
            }
        } catch (e: Exception) {
            send(Resource.failed(e.message ?: "Terjadi kesalahan!", null))
            logs("Error : " + e.message)
        }
    }

//    fun register(data: RegisterRequest) = flow {
//        emit(Resource.loading(null))
//        try {
//            remote.register(data).let {
//                if (it.isSuccessful) {
//                    SharedPref.isLogin = true
//                    val body = it.body()
//                    val user = body?.data
//                    SharedPref.setUser(user)
//                    emit(Resource.success(user))
//                    logs("Berhasil : " + body.toString())
//                } else {
//                    emit(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
//                }
//            }
//        } catch (e: Exception) {
//            emit(Resource.failed(e.message?: "Terjadi kesalahan!", null))
//        }
//    }

    fun updateUser(data: UserRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Pref.setUser(user)
                    emit(Resource.success(user))
                    logs("Berhasil : " + body.toString())
                } else {
                    emit(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.failed(e.message?: "Terjadi kesalahan!", null))
        }
    }

    fun uploadUser(token: String?, id: Int? = null, fileImage: MultipartBody.Part? = null) = channelFlow {
        send(Resource.loading(null))
        try {
            remote.uploadUser(token, id, fileImage).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Pref.setUser(user)
                    send(Resource.success(user))
                    logs("Berhasil : " + body.toString())
                } else {
                    send(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
                }
            }
        } catch (e: Exception) {
            send(Resource.failed(e.message?: "Terjadi kesalahan!", null))
        }
    }

}