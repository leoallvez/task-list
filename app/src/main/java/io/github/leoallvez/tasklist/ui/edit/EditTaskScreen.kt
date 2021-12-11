package io.github.leoallvez.tasklist.ui.edit

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.ui.FormAppBar
import io.github.leoallvez.tasklist.ui.LoadingCentred
import io.github.leoallvez.tasklist.ui.TaskForm

@Composable
fun EditTaskScreen(
    viewModel: EditTaskViewModel?,
    taskId: Int?,
    navController: NavController?
) {
    val task: Task? = viewModel?.getTaskById(taskId)?.observeAsState(initial = null)?.value
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
                    isEditing = true
                ) { task ->
                    viewModel.updateTask(task)
                    Toast.makeText(
                        context,
                        R.string.edited_task,
                        Toast.LENGTH_LONG
                    ).show()
                    navController?.navigate(Screen.List.route)
                }
            }
        }
    )
}

@Preview
@Composable
fun EditTaskPreview() {
    EditTaskScreen(viewModel = null, taskId = null, navController = null)
}
