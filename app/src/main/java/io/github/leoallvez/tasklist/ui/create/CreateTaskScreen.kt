package io.github.leoallvez.tasklist.ui.create

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateTaskScreen(viewModel: CreateTaskViewModel = viewModel()) {
    Text(text = "Create task screen")
}