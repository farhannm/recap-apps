package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.model.Pekerjaan
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

    @GET("api/user-current-month/{id}")
    suspend fun getUserCurrentMonth(
        @Path("id") id: Int? = null
    ) : Response<BaseSingleResponse<DetailPekerjaan>>

    @GET("api/user/{id}")
    suspend fun getUserId(
        @Path("id") int: Int? = null,
    ) : Response<BaseSingleResponse<User>>
    @GET("api/user-total-by-month/{id}/{id_pk}")
    suspend fun getSelectedTotalJam(
        @Path("id") id: Int? = null,
        @Path("id_pk") id_pk: Int? = null
    ) : Response<BaseSingleResponse<DetailPekerjaan>>

    @GET("api/user-task-by-month/{id}/{id_pk}")
    suspend fun getUserTaskByMonth(
        @Path("id") id: Int? = null,
        @Path("id_pk") id_pk: Int? = null
    ) : Response<BaseListResponse<DetailPekerjaan>>

    @GET("api/search-user/nama")
    suspend fun searchUser(
        @Query("nama") nama: String?,
    ) : Response<BaseSingleResponse<UserResponse>>

    @POST("api/user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int? = null,
        @Part data: UserRequest
    ) : Response<BaseSingleResponse<UserResponse>>

    @PUT("api/user/{id}")
    suspend fun changePass(
        @Path("id") int: Int? = null,
        @Body data: UserRequest
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
    suspend fun getPekerjaan(): Response<BaseListResponse<Pekerjaan>>

    @GET("api/pekerjaan-current-month")
    suspend fun getPekerjaanMonth(): Response<BaseSingleResponse<Pekerjaan>>

    @GET("api/selected-pekerjaan/{id}")
    suspend fun getSelectedPekerjaan(
        @Path("id") int: Int? = null,
    ) : Response<BaseSingleResponse<Pekerjaan>>

    @GET("api/count-karyawan")
    suspend fun getCountKaryawan(): Response<BaseSingleResponse<Pekerjaan>>

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

    @GET("api/user-today-task/{id}")
    suspend fun getUserTodayTask(
        @Path("id") int: Int? = null
    ) : Response<BaseListResponse<DetailPekerjaan>>

    @GET("api/user-count-today-task/{id}")
    suspend fun getUserCountTodayTask(
        @Path("id") int: Int? = null
    ) : Response<BaseSingleResponse<User>>

    @GET("api/detailpk-show/{id}")
    suspend fun getDetailPekerjaan(
        @Path("id") int: Int? = null,
    ) : Response<BaseSingleResponse<DetailPekerjaan>>

    @POST("api/detailpk-create/{id}")
    suspend fun  createDetailPekerjaan(
        @Path("id") int: Int? = null,
        @Body data : DetailPkRequest,
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @PUT("api/detailpk-update/{id}")
    suspend fun updateDetailPk(
        @Path("id") int: Int? = null,
        @Body data: DetailPkRequest
    ) : Response<BaseSingleResponse<DetailPkResponse>>

    @DELETE("api/detailpk-delete/{id}")
    suspend fun deleteDetailPk(
        @Path("id") int: Int? = null,
    ) : Response<BaseSingleResponse<DetailPkResponse>>


}