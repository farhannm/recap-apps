package com.bara.recapitulation.core.data.source.remote.response


data class BaseResponse<T>(
    val code: Int?,
    val message: String?,
    val token: String?,
    val data: T?,
)
