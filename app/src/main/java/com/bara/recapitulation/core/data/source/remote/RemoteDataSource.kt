package com.bara.recapitulation.core.data.source.remote

import com.bara.recapitulation.core.data.source.remote.network.ApiService
import com.bara.recapitulation.core.data.source.remote.request.*
import com.bara.recapitulation.util.getUserId
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: AuthRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.createUser(data)

    suspend fun getUser() = api.getUser()
    suspend fun getUserByPekerjaan(id: Int? = null) = api.getUserByPekerjaan(id)
    suspend fun getSingleUser() = api.getUserId(getUserId())
    suspend fun getUserTaskByMonth(id_pk: Int? = null) = api.getUserTaskByMonth(getUserId(), id_pk)
    suspend fun getTaskById(id: Int? = null, id_pk: Int? = null) = api.getTaskById(id, id_pk)
    suspend fun getSelectedTotalJam(id_pk: Int? = null) = api.getSelectedTotalJam(getUserId(), id_pk)
    suspend fun getTotalJamByUser(id: Int? = null, id_pk: Int? = null) = api.getSelectedTotalJam(id, id_pk)
    suspend fun getCountKaryawan() = api.getCountKaryawan()
    suspend fun getUserCurrentMonth() = api.getUserCurrentMonth(getUserId())
    suspend fun getUserCountTodayTask() = api.getUserCountTodayTask(getUserId())

    suspend fun searchUser(nama: String?) = api.searchUser(nama)
    suspend fun updateUser(data: UserRequest) = api.updateUser(getUserId(), data)
    suspend fun changePass(data: UserRequest) = api.changePass(getUserId(), data)
    suspend fun uploadUser(fileImage: MultipartBody.Part? = null) = api.uploadUser(getUserId(), fileImage)
    suspend fun createPekerjaan(data: PkRequest) = api.createPekerjaan(data)
    suspend fun getPekerjaan() = api.getPekerjaan()
    suspend fun getPekerjaanMonth() = api.getPekerjaanMonth()
    suspend fun getSelectedPekerjaan(id_pk: Int? = null) = api.getSelectedPekerjaan(id_pk)
    suspend fun getUserTodayTask() = api.getUserTodayTask(getUserId())
    suspend fun getDetailPk(id: Int? = null) = api.getDetailPekerjaan(id)
    suspend fun createDetailPk(data: DetailPkRequest) = api.createDetailPekerjaan(getUserId(), data)
    suspend fun updateDetailPk(id: Int? = null, data: DetailPkRequest) = api.updateDetailPk(id, data)
    suspend fun deleteDetailPk(id: Int? = null) = api.deleteDetailPk(id)


}