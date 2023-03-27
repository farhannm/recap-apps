package com.bara.recapitulation.core.data.source.remote.network

import com.bara.recapitulation.util.Pref
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder  = chain.request().newBuilder()
            .header("Authorization", Pref.token)

        return chain.proceed(requestBuilder.build())
    }

}