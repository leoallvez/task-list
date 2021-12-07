package io.github.leoallvez.tasklist.repository

import io.github.leoallvez.tasklist.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {

    suspend fun create(task: Task)

    suspend fun getAll(): Flow<List<Task>>

    suspend fun update(task: Task)

    suspend fun delete(task: Task)
}