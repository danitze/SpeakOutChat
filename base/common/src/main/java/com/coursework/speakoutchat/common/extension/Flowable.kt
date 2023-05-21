package com.coursework.speakoutchat.common.extension

import io.reactivex.Flowable
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun <T> Flowable<T>.toFlow(): Flow<T> = callbackFlow {
    val disposable = subscribe { value ->
        launch {
            send(value)
        }
    }

    awaitClose {
        disposable.dispose()
    }
}