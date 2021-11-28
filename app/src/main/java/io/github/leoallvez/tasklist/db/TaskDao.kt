package io.github.leoallvez.tasklist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.leoallvez.tasklist.model.Task

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAll(): LiveData<List<Task>>

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}
