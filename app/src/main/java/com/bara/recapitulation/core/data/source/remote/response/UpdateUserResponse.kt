package com.bara.recapitulation.core.data.source.remote.response

import com.bara.recapitulation.core.data.source.model.User

data class UpdateUserResponse(
    val token: String? = null,
    val data: User? = null,
    val pesan: String? = null
)