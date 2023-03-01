package com.bara.recapitulation.core.data.source.remote

import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.network.ApiService
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: AuthRequest) = api.login(data)
    suspend fun getUser(id:Int?=null) = api.getUser(id)
}