package io.github.leoallvez.tasklist.ui.edit

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.ui.FormAppBar
import io.github.leoallvez.tasklist.ui.LoadingCentred
import io.github.leoallvez.tasklist.ui.TaskForm

@Composable
fun EditTaskScreen(
    viewModel: EditTaskViewModel = hiltViewModel(),
    taskId: Int?,
    navController: NavController?
) {
    viewModel.recoverTaskById(id = taskId)
    val task: Task? = viewModel.task.observeAsState(initial = null).value
    Scaffold(
        topBar = {
            FormAppBar(titleId = R.string.edit_task) {
                navController?.navigate(Screen.List.route)
            }
        },
        content = {
            if(task == null) {
                LoadingCentred()
            } else {
                val context = LocalContext.current
                TaskForm(
                    task = task,
                    isEditing = false
                ) { task ->
                    viewModel.updateTask(task)
                    Toast.makeText(
                        context,
                        R.string.edit_task,
                        Toast.LENGTH_LONG
                    ).show()
                    navController?.navigate(Screen.List.route)
                }
            }
        }
    )
}