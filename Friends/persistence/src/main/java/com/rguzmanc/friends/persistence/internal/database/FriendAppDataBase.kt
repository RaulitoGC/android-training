package com.rguzmanc.friends.persistence.internal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rguzmanc.friends.persistence.AppDataBase
import com.rguzmanc.friends.persistence.internal.database.dao.FriendDao
import com.rguzmanc.friends.persistence.internal.database.entity.Friend

@Database(entities = [Friend::class], version = 1)
internal abstract class FriendAppDataBase : RoomDatabase(), AppDataBase {
    abstract override fun userDao(): FriendDao
}