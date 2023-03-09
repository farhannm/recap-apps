package com.bara.recapitulation.core.data.source.remote

import com.bara.recapitulation.core.data.source.remote.network.ApiService
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.core.data.source.remote.request.UpdateUserRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: AuthRequest) = api.login(data)
//    suspend fun register(data: RegisterRequest) = api.register(data)
    suspend fun updateUser(data: UpdateUserRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(token: String? = null, id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploadUser(token, id, fileImage)
}