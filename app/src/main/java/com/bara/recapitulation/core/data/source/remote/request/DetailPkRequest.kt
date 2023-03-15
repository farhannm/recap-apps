package com.bara.recapitulation.core.data.source.remote.request

data class DetailPkRequest(
    val desc_pekerjaan: String? = null,
    val id: Int? = null,
    val id_pekerjaan: Int? = null,
    val jam_kerja: Int? = null,
    val nama_pekerjaan: String? = null,
    val tgl_kerja: String? = null,
    val tipe: String? = null,
)