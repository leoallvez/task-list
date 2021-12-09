package io.github.leoallvez.tasklist.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.ITaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

//    init {
//        createATask()
//    }
//
//    private fun createATask() = viewModelScope.launch {
//        val i = (0..10000).random()
//        createTask(Task(id = 0, title = "title$i", description = "description$i", Task.PENDING))
//    }

    fun createTask(task: Task) = viewModelScope.launch {
        _repository.create(task)
    }
}