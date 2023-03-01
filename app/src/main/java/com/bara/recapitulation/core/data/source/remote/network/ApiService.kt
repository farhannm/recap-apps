package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.core.data.source.remote.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body auth: AuthRequest
    ) : Response<AuthResponse>

    @GET("user/{id}")
    suspend fun getUser(
        @Path("id") int:Int? = null
    ) : Response<AuthResponse>
}