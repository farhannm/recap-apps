package com.bara.recapitulation.core.data.source.remote.request

data class PkRequest(
    val bulan: String? = null,
    val end: String? = null,
    val id: Int? = null,
    val id_user: Int? = null,
    val jam_toleransi: Int? = null,
    val start: String? = null,
    val total_jam: Int? = null,
)