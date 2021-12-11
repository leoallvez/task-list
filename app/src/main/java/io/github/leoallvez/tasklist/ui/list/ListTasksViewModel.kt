package io.github.leoallvez.tasklist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.ITaskRepository
import javax.inject.Inject

@HiltViewModel
class ListTasksViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

    fun getTasks(): LiveData<List<Task>> = liveData {
        emitSource(_repository.getAll().asLiveData())
    }
}