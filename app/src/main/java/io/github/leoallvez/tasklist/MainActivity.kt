package io.github.leoallvez.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.leoallvez.tasklist.ui.create.CreateTaskScreen
import io.github.leoallvez.tasklist.ui.edit.EditTaskScreen
import io.github.leoallvez.tasklist.ui.list.ListTasksScreen
import io.github.leoallvez.tasklist.ui.theme.TaskListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TaskApp()
                }
            }
        }
    }
}

@Composable
fun TaskApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(route = Screen.List.route) {
            ListTasksScreen(nav = navController)
        }
        composable(route = Screen.Create.route) {
            CreateTaskScreen()
        }
        composable(
            route = Screen.Edit.route,
            arguments = listOf(navArgument(name = "task_id") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val taskId = navBackStackEntry.arguments?.getInt("task_id")
            EditTaskScreen(taskId = taskId)
        }
    }
}