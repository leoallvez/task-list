package io.github.leoallvez.tasklist.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.leoallvez.tasklist.ui.theme.Purple700

@Composable
fun AddTaskButton(onClick: () -> Unit) {
    FloatingActionButton(
        backgroundColor = Purple700,
        onClick = onClick
    ) {
        Text("+")
    }
}

@Composable
fun AppBar(@StringRes titleId: Int) {
    TopAppBar(backgroundColor = Purple700) {
        Text(
            text = stringResource(id = titleId),
            modifier = Modifier.padding(start = 5.dp),
            color = Color.White
        )
    }
}