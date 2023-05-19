package com.coursework.speakoutchat.network.interceptor

import com.coursework.speakoutchat.network.util.NetworkConstants.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BearerTokenInterceptor @Inject constructor() : Interceptor {

    @Volatile
    var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (token != null) {
            request = request.newBuilder()
                .addHeader(AUTHORIZATION, "Bearer $token")
                .build()
        }
        return chain.proceed(request)
    }
}