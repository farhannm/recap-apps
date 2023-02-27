package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.core.data.source.remote.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body auth: AuthRequest
    ) : Response<AuthResponse>

    @GET
    suspend fun getData(
        @Body user: User
    ) : Response<AuthResponse>
}