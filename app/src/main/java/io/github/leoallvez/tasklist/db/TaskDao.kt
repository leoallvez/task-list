package io.github.leoallvez.tasklist.db

import androidx.room.*
import io.github.leoallvez.tasklist.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks order by id desc;")
    fun getAll(): Flow<List<Task>>

    @Update
    suspend fun update(task: Task)

    @Query("DELETE FROM tasks WHERE id = :id;")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM tasks WHERE id = :id;")
    fun getById(id: Int): Flow<Task>
}
