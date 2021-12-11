package io.github.leoallvez.tasklist.repository

import io.github.leoallvez.tasklist.db.TaskDatabase
import io.github.leoallvez.tasklist.di.IoDispatcher
import io.github.leoallvez.tasklist.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val _database: TaskDatabase,
    @IoDispatcher private val _dispatcher: CoroutineDispatcher
) : ITaskRepository {

    override suspend fun create(task: Task) {
        withContext(_dispatcher) {
            _database.taskDao().insert(task)
        }
    }

    override suspend fun getAll(): Flow<List<Task>> {
        return withContext(_dispatcher) {
            _database.taskDao().getAll()
        }
    }

    override suspend fun update(task: Task) {
        withContext(_dispatcher) {
            _database.taskDao().update(task)
        }
    }

    override suspend fun delete(task: Task) {
        withContext(_dispatcher) {
            _database.taskDao().delete(task)
        }
    }

    override suspend fun getById(id: Int): Flow<Task> {
        return withContext(_dispatcher) {
            _database.taskDao().getById(id)
        }
    }
}
