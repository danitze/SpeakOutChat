package com.coursework.speakoutchat.auth_domain_data.di

import com.coursework.speakoutchat.auth_domain_data.login.LoginApiService
import com.coursework.speakoutchat.auth_domain_data.sign_up.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AuthNetworkModule {

    @Provides
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService = retrofit
        .create(SignUpApiService::class.java)

    @Provides
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService = retrofit
        .create(LoginApiService::class.java)

}