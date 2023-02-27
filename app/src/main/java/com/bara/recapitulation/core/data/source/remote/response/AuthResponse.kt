package com.bara.recapitulation.core.data.source.remote.response

import com.bara.recapitulation.core.data.source.model.User

data class AuthResponse (
    val code: Int? = null,
    val message: String? = null,
    val data: User? = null
)