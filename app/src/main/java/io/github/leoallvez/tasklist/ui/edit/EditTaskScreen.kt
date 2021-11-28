package io.github.leoallvez.tasklist.ui.edit

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditTaskScreen(
    viewModel: EditTaskViewModel = hiltViewModel(),
    taskId: Int?
) {
    Text(text = "editing id: $taskId")
}