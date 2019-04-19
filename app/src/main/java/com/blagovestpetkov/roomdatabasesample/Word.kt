package com.blagovestpetkov.roomdatabasesample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "word_table")
data class Word(@PrimaryKey @NotNull @ColumnInfo(name = "word") val word: String)