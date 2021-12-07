package io.github.leoallvez.tasklist.ui.list

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.model.Task.Companion.PENDING
import io.github.leoallvez.tasklist.repository.ITaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTasksViewModel @Inject constructor(
    private val _repository: ITaskRepository
) : ViewModel() {

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            for(i in 101..200) {
//                _repository.create(Task(id = 0, title = "title$i", description = "description$i", PENDING))
//            }
//        }
    }

    fun getTasks(): LiveData<List<Task>> = liveData {
        //emit(listOf())
        emitSource(_repository.getAll().asLiveData())
    }
}