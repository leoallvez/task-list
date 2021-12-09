package io.github.leoallvez.tasklist.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun FormAppBar(@StringRes titleId: Int, backCallback: () -> Unit) {
    AppBar(titleId = titleId) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back button",
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable(onClick = backCallback),
        )
    }
}

@Composable
fun AppBar(@StringRes titleId: Int, content: @Composable RowScope.() -> Unit) {
    TopAppBar(backgroundColor = Purple700) {
        Row {
            content()
            Text(
                text = stringResource(id = titleId),
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White
            )
        }
    }
}