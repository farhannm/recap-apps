package com.bara.recapitulation.core.data.source.remote.response


data class BaseResponse<T>(
    val code: Int?,
    val message: String?,
    val data: T?,
)
