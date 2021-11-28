package io.github.leoallvez.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TaskRepository @Inject constructor() {

    fun getTasks(): LiveData<List<Task>> {
        val taskList = mutableListOf<Task>()
        for(i in 0..100) {
            taskList.add(Task(id = i, title = "title $i", description = "description $i", status = "pending"))
        }
        return MutableLiveData(taskList)
    }
}