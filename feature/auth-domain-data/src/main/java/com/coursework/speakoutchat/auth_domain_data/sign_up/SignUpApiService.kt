package com.coursework.speakoutchat.auth_domain_data.sign_up

import com.coursework.speakoutchat.auth_domain_data.data.UserCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApiService {

    @POST("/auth/register")
    suspend fun signUp(@Body userCredentials: UserCredentials)

}