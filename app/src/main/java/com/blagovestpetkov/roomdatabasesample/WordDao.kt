package com.blagovestpetkov.roomdatabasesample

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Delete
    fun deleteWord(word: Word)

    @Query("SELECT * from word_table LIMIT 1")
    fun getAnyWord(): Array<Word>
}