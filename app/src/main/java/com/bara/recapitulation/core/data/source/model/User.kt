package com.bara.recapitulation.core.data.source.model

data class User(
    val alamat: String,
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val id: Int,
    val id_role: Int,
    val image: Any,
    val nama: String,
    val password: String,
    val status: String,
    val telp: String,
    val updated_at: String
)