package com.bara.recapitulation.core.data.source.remote

import com.bara.recapitulation.core.data.source.remote.network.ApiService
import com.bara.recapitulation.core.data.source.remote.request.*
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: AuthRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.createUser(data)
    suspend fun updateUser(data: UserRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(token: String? = null, id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploadUser(token, id, fileImage)
    suspend fun createPekerjaan(data: PkRequest) = api.createPekerjaan(data)
    suspend fun createDetailPk(data: DetailPkRequest) = api.createDetailPekerjaan(data)
    suspend fun updateDetailPk(data: DetailPkRequest) = api.createDetailPekerjaan(data)
}