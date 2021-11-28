package io.github.leoallvez.tasklist.ui.create

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateTaskScreen(viewModel: CreateTaskViewModel = hiltViewModel()) {
    Text(text = "Create task screen")
}