package com.blagovestpetkov.roomdatabasesample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

@WorkerThread
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }
}