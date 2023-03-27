package com.bara.recapitulation.core.data.repository

import com.bara.recapitulation.core.data.source.local.LocalDataSource
import com.bara.recapitulation.core.data.source.remote.RemoteDataSource
import com.bara.recapitulation.core.data.source.remote.network.Resource
import com.bara.recapitulation.core.data.source.remote.request.*
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
                    Pref.setUser(user)
                    Pref.token = user?.token ?: "Token expired"
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

    fun register(data: RegisterRequest) = channelFlow {
        send(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    send(Resource.success(data))
                    logs("Berhasil : " + body.toString())
                } else {
                    send(Resource.failed(it.getErrorBody()?.message ?: "Default error.", null))
                }
            }
        } catch (e: Exception) {
            send(Resource.failed(e.message?: "Terjadi kesalahan!", null))
        }
    }

    fun getUser() = flow {
        emit(Resource.loading(null))
        try {
            remote.index().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
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

    fun createPekerjaan(data: PkRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createPekerjaan(data).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Pref.setUserPk(user)
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

    fun createDetailPk(data: DetailPkRequest, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.createDetailPk(data, fileImage).let {
                if (it.isSuccessful) {
                    Pref.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Pref.setUserDetailPk(user)
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

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = channelFlow {
        send(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
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