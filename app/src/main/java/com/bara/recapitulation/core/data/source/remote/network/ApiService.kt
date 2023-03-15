package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.remote.request.*
import com.bara.recapitulation.core.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //Auth
    @POST("login")
    suspend fun login(
        @Body auth: AuthRequest
    ) : Response<BaseResponse<AuthResponse>>

    @POST("register")
    suspend fun createUser(
        @Body auth: RegisterRequest
    ) : Response<BaseResponse<AuthResponse>>

    //User
    @GET("api/user")
    suspend fun getAllUser() : Response<BaseResponse<UserResponse>>

    @GET("api/user/{id}")
    suspend fun getUserId(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseResponse<UserResponse>>

    @GET("api/search-user/nama")
    suspend fun searchUser(
        @Path("nama") nama: String? = null,
        @Part data: UserRequest
    ) : Response<BaseResponse<UserResponse>>

    @POST("api/user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseResponse<UserResponse>>

    @Multipart
    @POST("api/upload-user/{id}")
    suspend fun uploadUser(
        @Header("Authorization") api_token : String?,
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ) : Response<BaseResponse<UserResponse>>

    @DELETE("api/user/{id}")
    suspend fun deleteUser(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseResponse<UserResponse>>

    //Pekerjaan
    @GET("api/pekerjaan")
    suspend fun getAllPekerjaan() : Response<BaseResponse<PkResponse>>

    @GET("api/pekerjaan/{idUser}")
    suspend fun getPekerjaanUser(
        @Path("idUser") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseResponse<PkResponse>>

    @GET("api/search-pekerjaan/{bulan}")
    suspend fun searchPekerjaan(
        @Path("bulan") bulan: String? = null,
        @Part data: PkRequest
    ) : Response<BaseResponse<PkResponse>>

    @POST("api/pekerjaan")
    suspend fun createPekerjaan(
        @Body pekerjaan: PkRequest
    ) : Response<BaseResponse<PkResponse>>

    @PUT("api/pekerjaan/{id}")
    suspend fun updatePk(
        @Path("id") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseResponse<PkResponse>>

    @DELETE("api/pekerjaan/{id}")
    suspend fun deletePk(
        @Path("id") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseResponse<PkResponse>>

    //Detail Pekerjaan
    @GET("api/detailpk")
    suspend fun getAllDetailPk() : Response<BaseResponse<DetailPkResponse>>

    @GET("api/pekerjaan/{id}")
    suspend fun getDetailPkUser(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseResponse<DetailPkResponse>>

    @GET("api/search-detailpk/{namaPekerjaan}")
    suspend fun searchDetailPekerjaan(
        @Path("namaPekerjaan") title: String? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseResponse<DetailPkResponse>>

    @POST("api/detailpk")
    suspend fun  createDetailPekerjaan(
        @Body detail_pk : DetailPkRequest
    ) : Response<BaseResponse<DetailPkResponse>>

    @PUT("api/detailpk/{id}")
    suspend fun updateDetailPk(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseResponse<DetailPkResponse>>

    @DELETE("api/detailpk/{id}")
    suspend fun deleteDetailPk(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseResponse<DetailPkResponse>>


}