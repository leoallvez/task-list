package io.github.leoallvez.tasklist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.TaskRepository
import javax.inject.Inject

@HiltViewModel
class ListTasksViewModel @Inject constructor(repository: TaskRepository) : ViewModel() {

    val task: LiveData<List<Task>> = repository.getTasks()

}