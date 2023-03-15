package com.bara.recapitulation.core.data.source.remote.request

data class RegisterRequest (
    val nama: String? = null,
    val email: String? = null,
    val password: String? = null,
    val telp: String? = null,
    val alamat: String? = null,
    val status: String? = null
)