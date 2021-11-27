package io.github.leoallvez.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import io.github.leoallvez.tasklist.ui.theme.TaskListTheme
import io.github.leoallvez.tasklist.ui.list.TaskListScreen
import io.github.leoallvez.tasklist.ui.list.makeTaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TaskListScreen(
                        appName = getString(R.string.app_name),
                        tasks = makeTaskList()
                    )
                }
            }
        }
    }
}