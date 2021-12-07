package io.github.leoallvez.tasklist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.leoallvez.tasklist.model.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}