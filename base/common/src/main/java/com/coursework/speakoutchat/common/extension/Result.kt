package com.coursework.speakoutchat.common.extension

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>): Result<R> {
    if (isFailure) {
        val exception = requireNotNull(exceptionOrNull()) { "Exception is present" }
        return Result.failure(exception)
    }
    return transform.invoke(getOrThrow())
}

fun <T> Result<T>.getOrDefault(defaultValue: T): T = getOrNull() ?: defaultValue