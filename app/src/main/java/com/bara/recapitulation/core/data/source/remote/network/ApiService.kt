package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.model.User
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
    ) : Response<BaseSingleResponse<UserResponse>>

    @POST("api/register")
    suspend fun createUser(
        @Body auth: RegisterRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    //User
    @GET("api/user")
    suspend fun getUser(): Response<BaseListResponse<User>>

    @GET("api/user/{id}")
    suspend fun getUserId(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    @GET("api/search-user/nama")
    suspend fun searchUser(
        @Path("nama") nama: String? = null,
        @Part data: UserRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    @POST("api/user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    @Multipart
    @POST("api/upload-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ) : Response<BaseSingleResponse<UserResponse>>

    @DELETE("api/user/{id}")
    suspend fun deleteUser(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    //Pekerjaan
    @GET("api/pekerjaan")
    suspend fun getAllPekerjaan() : Response<BaseSingleResponse<PkResponse>>

    @GET("api/pekerjaan/{idUser}")
    suspend fun getPekerjaanUser(
        @Path("idUser") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseSingleResponse<PkResponse>>

    @GET("api/search-pekerjaan/{bulan}")
    suspend fun searchPekerjaan(
        @Path("bulan") bulan: String? = null,
        @Part data: PkRequest
    ) : Response<BaseSingleResponse<PkResponse>>

    @POST("api/pekerjaan")
    suspend fun createPekerjaan(
        @Body pekerjaan: PkRequest
    ) : Response<BaseSingleResponse<PkResponse>>

    @PUT("api/pekerjaan/{id}")
    suspend fun updatePk(
        @Path("id") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseSingleResponse<PkResponse>>

    @DELETE("api/pekerjaan/{id}")
    suspend fun deletePk(
        @Path("id") int: Int? = null,
        @Part data: PkRequest
    ) : Response<BaseSingleResponse<PkResponse>>

    //Detail Pekerjaan
    @GET("api/detailpk")
    suspend fun getAllDetailPk() : Response<BaseSingleResponse<DetailPkResponse>>

    @GET("api/pekerjaan/{id}")
    suspend fun getDetailPkUser(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @GET("api/search-detailpk/{namaPekerjaan}")
    suspend fun searchDetailPekerjaan(
        @Path("namaPekerjaan") title: String? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @POST("api/detailpk")
    suspend fun  createDetailPekerjaan(
        @Body detail_pk : DetailPkRequest,
        @Part bukti: MultipartBody.Part? = null
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @PUT("api/detailpk/{id}")
    suspend fun updateDetailPk(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @DELETE("api/detailpk/{id}")
    suspend fun deleteDetailPk(
        @Path("id") int: Int? = null,
        @Part data: DetailPkRequest
    ) : Response<BaseSingleResponse<DetailPkResponse>>


}