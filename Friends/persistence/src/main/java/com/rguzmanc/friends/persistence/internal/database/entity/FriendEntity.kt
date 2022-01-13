package com.rguzmanc.friends.persistence.internal.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend(
    @PrimaryKey val uid: Int,
    val firstName: String,
    val lastName: String
)