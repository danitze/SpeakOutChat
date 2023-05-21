package com.coursework.speakoutchat.auth_domain_data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "token")
    val token: String
)