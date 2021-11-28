package io.github.leoallvez.tasklist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.leoallvez.tasklist.model.Task

@Database(entities = [Task::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}