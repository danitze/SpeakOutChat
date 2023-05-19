package com.coursework.speakoutchat.auth_domain_data.login

import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/auth/token")
    suspend fun authenticate(@Body authRequest: AuthRequest): Response<ResponseBody>

}