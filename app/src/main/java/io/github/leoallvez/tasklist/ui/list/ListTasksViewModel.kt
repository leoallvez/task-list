package io.github.leoallvez.tasklist.ui.list

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.repository.ITaskRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTasksViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _task: MutableLiveData<List<Task>> = MutableLiveData(listOf())
    val tasks: LiveData<List<Task>> = _task

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _repository.getAll().collect {
                _task.value = it
                _isRefreshing.value = false
            }
        }
    }
}