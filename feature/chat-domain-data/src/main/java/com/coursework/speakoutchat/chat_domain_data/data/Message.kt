package com.coursework.speakoutchat.chat_domain_data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "message")
data class Message(

    @Transient
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,

    @ColumnInfo(name = "sender_id")
    val senderId: String,

    @ColumnInfo(name = "receiver_id")
    val receiverId: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long

)