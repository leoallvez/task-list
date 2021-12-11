package io.github.leoallvez.tasklist.ui.create

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.ui.FormAppBar
import io.github.leoallvez.tasklist.ui.TaskForm

@Composable
fun CreateTaskScreen(
        viewModel: CreateTaskViewModel?,
        navController: NavController?
    ) {

    Scaffold(
        topBar = {
            FormAppBar(titleId = R.string.create_task) {
                navController?.navigate(Screen.List.route)
            }
        },
        content = {
            val context = LocalContext.current
            TaskForm(
                isEditing = false
            ) { task ->
                viewModel?.createTask(task)
                Toast.makeText(
                    context,
                    R.string.create_task,
                    Toast.LENGTH_LONG
                ).show()
                navController?.navigate(Screen.List.route)
            }
        }
    )
}

@Preview
@Composable
fun CreateTaskPreview() {
    CreateTaskScreen(viewModel = null, navController = null)
}