package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.core.data.source.remote.request.AuthRequest
import com.bara.recapitulation.core.data.source.remote.request.RegisterRequest
import com.bara.recapitulation.core.data.source.remote.request.UpdateUserRequest
import com.bara.recapitulation.core.data.source.remote.response.AuthResponse
import com.bara.recapitulation.core.data.source.remote.response.UpdateUserResponse
import com.bara.recapitulation.core.data.source.remote.response.UploadUserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body auth: AuthRequest
    ) : Response<AuthResponse>

    @POST("register")
    suspend fun createUser(
        @Body auth: RegisterRequest
    ) : Response<AuthResponse>

    @PUT("user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int? = null,
        @Part data: UpdateUserRequest
    ) : Response<UpdateUserResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser(
        @Header("Authorization") api_token : String?,
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ) : Response<UploadUserResponse>


}