package com.bara.recapitulation.core.data.source.remote.request

data class PkRequest(
    val bulan: String? = null,
    val start: String? = null,
    val end: String? = null,
    val total_jam: String? = null,
    val jam_toleransi: String? = null,
)