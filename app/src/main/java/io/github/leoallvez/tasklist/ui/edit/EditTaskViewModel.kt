package io.github.leoallvez.tasklist.ui.edit

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.ITaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

    fun getTaskById(id: Int?): LiveData<Task> = liveData {
        id?.let {
            emitSource(_repository.getById(id = id).asLiveData())
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        _repository.update(task = task)
    }
}
