package com.blagovestpetkov.roomdatabasesample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        private const val DB_NAME = "word_database"
        private val words = listOf("Hello", "World!")

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                return instance
            }
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        scope.launch(Dispatchers.IO) {
                            populateDatabase(database.wordDao())
                        }
                    }
                }
            }
        }

        private fun populateDatabase(wordDao: WordDao) {
            // If we have no words, then create the initial list of words
            if (wordDao.getAnyWord().isEmpty()) {
                for (word in words) {
                    wordDao.insert(Word(word))
                }
            }
        }
    }
}