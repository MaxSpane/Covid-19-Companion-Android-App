package it.weMake.covid19Companion.practice

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//creating a table for room database
@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String) {
}