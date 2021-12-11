package io.github.leoallvez.tasklist.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.model.Task
import io.github.leoallvez.tasklist.ui.AddTaskButton
import io.github.leoallvez.tasklist.ui.AppBar
import io.github.leoallvez.tasklist.ui.LoadingCentred

@Composable
fun ListTasksScreen(
    viewModel: ListTasksViewModel?,
    navController: NavController?
) {
    viewModel?.refresh()
    val tasks = viewModel?.tasks?.observeAsState(initial = null)?.value

    Scaffold(topBar = { AppBar(titleId = R.string.app_name) {} },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AddTaskButton {
                navController?.navigate(route = Screen.Create.route)
            }
        },
        content = {
            when {
                tasks == null -> {
                    LoadingCentred()
                }
                tasks.isEmpty() -> {
                    NoTaskFoundMessage()
                } else -> {

                    val isRefreshing by viewModel.isRefreshing.observeAsState(false)

                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing),
                        onRefresh = { viewModel.refresh() },
                    ) {
                        TaskList(tasks) { taskId ->
                            navController?.navigate(route = Screen.Edit.editRoute(taskId))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun NoTaskFoundMessage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.no_tasks_found),
            style = MaterialTheme.typography.body2,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500
        )
    }
}

@Composable
fun TaskList(tasks: List<Task>, onClickItem: (taskId: Int) -> Unit) {
    val padding = 10.dp
    Surface(color = Color.LightGray, modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .padding(start = padding, end = padding)
                .fillMaxSize()
        ) {
            items(tasks) { task ->
                TaskItem(task) {
                    onClickItem.invoke(task.id)
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
fun ListTasksScreen() {
    ListTasksScreen(viewModel = null, navController = null)
}
