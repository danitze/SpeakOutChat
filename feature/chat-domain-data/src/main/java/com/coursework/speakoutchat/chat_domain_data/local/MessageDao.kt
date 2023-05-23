package com.coursework.speakoutchat.chat_domain_data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.coursework.speakoutchat.chat_domain_data.data.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM message ORDER BY time_stamp ASC")
    fun observeMessages(): Flow<List<Message>>

    @Upsert
    suspend fun upsertMessage(message: Message)

}