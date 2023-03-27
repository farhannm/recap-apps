package com.bara.recapitulation.core.data.source.remote.response

data class BaseListResponse<T>(
    val code: Int?,
    val message: String?,
    val data: List<T> = emptyList()
)
