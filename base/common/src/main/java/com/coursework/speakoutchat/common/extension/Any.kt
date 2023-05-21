package com.coursework.speakoutchat.common.extension

fun <T> T?.require(message: String? = null): T {
    return requireNotNull(this) { message ?: "Value is not null" }
}