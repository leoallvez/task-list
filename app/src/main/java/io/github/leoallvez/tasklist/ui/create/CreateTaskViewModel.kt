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

    fun createTask(task: Task) = viewModelScope.launch {
        _repository.create(task)
    }
}
