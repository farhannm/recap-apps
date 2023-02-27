package com.bara.recapitulation.core.data.source.model

data class User(
    val created_at: Any? = null,
    val deleted_at: Any? = null,
    val email: String?,
    val roles: String?,
    val email_verified_at: Any?,
    val id: Int?,
    val name: String?,
    val updated_at: Any?
)