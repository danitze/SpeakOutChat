package com.coursework.speakoutchat.auth_domain_data.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest(

    @Json(name = "name")
    val name: String,

    @Json(name = "password")
    val password: String
)
