package com.rguzmanc.friends.persistence.internal.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rguzmanc.friends.persistence.internal.database.entity.Friend

@Dao
interface FriendDao {

    @Query("SELECT * FROM Friend")
    fun getAll(): List<Friend>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: Friend)
}