package com.bara.recapitulation.core.data.source.remote.response

import com.bara.recapitulation.core.data.source.model.User

data class UploadUserResponse(
    val data: User?,
    val pesan: String?,
    val token: String?
)