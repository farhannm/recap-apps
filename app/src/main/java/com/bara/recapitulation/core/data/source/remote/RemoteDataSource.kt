package com.bara.recapitulation.core.data.source.remote

import com.bara.recapitulation.core.data.source.remote.network.ApiService
import com.bara.recapitulation.core.data.source.remote.request.*
import com.bara.recapitulation.util.getUserId
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: AuthRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.createUser(data)
    suspend fun getUser() = api.getUser()
    suspend fun getUserCurrentMonth() = api.getUserCurrentMonth(getUserId())
    suspend fun searchUser(nama: String?) = api.searchUser(nama)
    suspend fun updateUser(data: UserRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(fileImage: MultipartBody.Part? = null) = api.uploadUser(getUserId(), fileImage)
    suspend fun createPekerjaan(data: PkRequest) = api.createPekerjaan(data)
    suspend fun getPekerjaan() = api.getPekerjaan()
    suspend fun getPekerjaanMonth() = api.getPekerjaanMonth()
    suspend fun getUserTodayTask() = api.getUserTodayTask(getUserId())
    suspend fun createDetailPk(data: DetailPkRequest, fileImage: MultipartBody.Part? = null) = api.createDetailPekerjaan(data, fileImage)
}