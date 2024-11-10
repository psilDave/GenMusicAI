package com.psil.genmusicai.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.psil.genmusicai.data.dao.MusicChatDao
import com.psil.genmusicai.data.data.MusicChatEntity

@Database(entities = [MusicChatEntity::class], version = 1)
abstract class MusicChatDatabase : RoomDatabase() {

    abstract fun musicChatDao(): MusicChatDao
}