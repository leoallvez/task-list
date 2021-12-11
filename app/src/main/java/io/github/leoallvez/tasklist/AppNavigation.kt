package io.github.leoallvez.tasklist

sealed class Screen(val route: String) {
    object List: Screen("list_task")
    object Create: Screen("create_task")
    object Edit: Screen("edit_task/{task_id}") {
        fun editRoute(id: Int): String = "edit_task/$id"
    }
}