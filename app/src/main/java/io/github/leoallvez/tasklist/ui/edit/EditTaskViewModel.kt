package io.github.leoallvez.tasklist.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.ITaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

    private val _task: MutableLiveData<Task> = MutableLiveData()
    val task: LiveData<Task> = _task

    fun recoverTaskById(id: Int?) = viewModelScope.launch {
        id?.let {
            _task.value = _repository.getTaskById(id = id)
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        _repository.update(task = task)
    }
}