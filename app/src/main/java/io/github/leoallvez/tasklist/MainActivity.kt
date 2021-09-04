package io.github.leoallvez.tasklist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.leoallvez.tasklist.ui.theme.Purple700
import io.github.leoallvez.tasklist.ui.theme.TaskListTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(
                        appName = getString(R.string.app_name),
                        tasks = makeTaskList()
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
    MainScreen(
        appName = "Nome do App",
        tasks = makeTaskList()
    )
}

@Composable
fun MainScreen(appName: String, tasks: List<Task>) {
    Scaffold(topBar = { MainAppBar(appName) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Text("+")
            }
        },
        content = {
            TaskList(tasks)
        }
    )
}

@Composable
fun MainAppBar(appName: String) {
    TopAppBar(backgroundColor = Purple700) {
        Text(
            text = appName,
            modifier = Modifier.padding(start = 5.dp),
            color = Color.White
        )
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    val padding = 10.dp
    LazyColumn(
        Modifier
            .padding(start = padding, end = padding)
            .fillMaxSize()
    ) {
        items(tasks) { task ->
            TaskRow(task)
        }
    }
}

private fun itemClicked() {
    Log.w("click", "item clicked")
}

@Composable
fun TaskRow(task: Task) {
    Row {
        val cardPadding = 2.dp
        Card(
            Modifier
                .padding(top = cardPadding, bottom = cardPadding)
                .height(40.dp)
                .clickable { itemClicked() }
                .fillMaxSize()
        ) {
            Column {
                val columnPadding = 10.dp
                Text(
                    text = task.title,
                    modifier = Modifier.padding(
                        top = columnPadding,
                        start = columnPadding
                    )
                )
            }
        }
    }
}

fun makeTaskList(): List<Task> {
    val taskList = mutableListOf<Task>()
    for(i in 0..100) {
        taskList.add(Task("title $i", "description $i", "pending"))
    }
    return taskList
}
