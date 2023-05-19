package com.coursework.speakoutchat.auth_domain_data.repository

import android.util.Log
import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import com.coursework.speakoutchat.auth_domain_data.data.UserCredentials
import com.coursework.speakoutchat.auth_domain_data.login.LoginNetSource
import com.coursework.speakoutchat.auth_domain_data.sign_up.SignUpNetSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val signUpNetSource: SignUpNetSource,
    private val loginNetSource: LoginNetSource
) {

    suspend fun signUp(userCredentials: UserCredentials): Result<Unit> {
        return signUpNetSource.signUp(userCredentials)
    }

    suspend fun login(authRequest: AuthRequest): Result<Unit> {
        return loginNetSource.authenticate(authRequest).map { Log.d("MyTag", it) }
    }

}