package com.bara.recapitulation.core.data.source.remote.request

data class DetailPkRequest(
//    val bukti_pekerjaan: String? = null,
    val id_user: String?= null,
    val id_pekerjaan: String?= null,
    val tipe: String? = null,
    val nama_pekerjaan: String? = null,
    val jam_kerja: String? = null,
    val tgl_kerja: String? = null,
    val desc_pekerjaan: String? = null,
)