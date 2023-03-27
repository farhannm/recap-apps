package com.bara.recapitulation.core.data.source.remote.response


data class BaseSingleResponse<T>(
    val code: Int?,
    val message: String?,
    val data: T?,
)
