package com.coursework.speakoutchat.auth_domain_data.mapper

import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import com.coursework.speakoutchat.auth_domain_data.data.UserInfo

fun mapToUserInfo(authRequest: AuthRequest, token: String): UserInfo = UserInfo(
    userId = authRequest.name,
    token = token
)