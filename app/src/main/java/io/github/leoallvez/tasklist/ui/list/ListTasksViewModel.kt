package io.github.leoallvez.tasklist.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.leoallvez.tasklist.Task

class ListTasksViewModel : ViewModel() {

    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val task: LiveData<List<Task>> = _tasks

    init {
        _tasks.value = makeTaskList()
    }

    private fun makeTaskList(): List<Task> {
        val taskList = mutableListOf<Task>()
        for(i in 0..100) {
            taskList.add(Task(id = i, title = "title $i", description = "description $i", status = "pending"))
        }
        return taskList
    }

}