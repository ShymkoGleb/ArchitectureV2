package com.example.architecturev2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.architecturev2.models.PostsResponse


@Database(entities = [PostsResponse::class], version = 1)
abstract class PostsDB: RoomDatabase()  {

    abstract fun getPostDao(): PostsDao

    companion object {
        @Volatile
        private var instance: PostsDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostsDB::class.java,
                "article_db.db"
            ).build()
    }
}
