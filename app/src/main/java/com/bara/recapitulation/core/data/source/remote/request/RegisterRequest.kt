package com.bara.recapitulation.core.data.source.remote.request

data class RegisterRequest (
    val nama: String,
    val email: String,
    val password: String,
    val telp: String,
    val alamat: String,
    val status: String
)