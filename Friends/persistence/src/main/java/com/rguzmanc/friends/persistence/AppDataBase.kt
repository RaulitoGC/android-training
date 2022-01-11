package com.rguzmanc.friends.persistence

import com.rguzmanc.friends.persistence.internal.database.dao.FriendDao

interface AppDataBase{
    fun userDao(): FriendDao
}