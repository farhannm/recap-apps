package com.bara.recapitulation.core.data.source.remote.request

data class RegisterRequest (
    val email: String? = null,
    val password: String? = null,
    val nama: String? = null,
    val jabatan: String? = null,
    val telp: String? = null,
    val alamat: String? = null,
)