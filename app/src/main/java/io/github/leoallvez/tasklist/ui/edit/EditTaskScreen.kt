package io.github.leoallvez.tasklist.ui.edit

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EditTaskScreen(
    viewModel: EditTaskViewModel,
    taskId: Int?
) {
    Text(text = "editing id: $taskId")
}