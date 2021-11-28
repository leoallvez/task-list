package io.github.leoallvez.tasklist.ui.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.leoallvez.tasklist.Task
import io.github.leoallvez.tasklist.ui.theme.Purple700
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.leoallvez.tasklist.R

@Composable
fun TaskListScreen(viewModel: TaskListViewModel = viewModel()) {
    val tasks by viewModel.task.observeAsState(initial = listOf())
    Scaffold(topBar = { AppBar() },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddTaskButton {}
        },
        content = {
            if(tasks.isEmpty()) {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "List is empty")
                }
            } else {
                TaskList(tasks)
            }
        }
    )
}

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
fun AppBar() {
    TopAppBar(backgroundColor = Purple700) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(start = 5.dp),
            color = Color.White
        )
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    val padding = 10.dp
    Surface(color = Color.LightGray, modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .padding(start = padding, end = padding)
                .fillMaxSize()
        ) {
            items(tasks) { task ->
                TaskItem(task) {
                    Log.w("click", "item clicked on task id: ${task.id}")
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClickItem: () -> Unit) {
    val cardPadding = 5.dp
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .padding(top = cardPadding, bottom = cardPadding)
            .height(80.dp)
            .clickable { onClickItem.invoke() }
            .fillMaxSize()
    ) {
        Column {
            val padding = 10.dp
            Text(
                text = task.title,
                modifier = Modifier.padding(
                    top = padding,
                    start = padding,
                ),
                style = MaterialTheme.typography.h6,
                fontSize = 16.sp
            )
            Text(
                text = task.description,
                modifier = Modifier.padding(
                    top = padding,
                    start = padding,
                ),
                style = MaterialTheme.typography.h6,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
    TaskListScreen()
}
