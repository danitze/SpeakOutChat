package com.coursework.speakoutchat.common.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.mapToUnit(): Flow<Unit> = map { /*stub */ }