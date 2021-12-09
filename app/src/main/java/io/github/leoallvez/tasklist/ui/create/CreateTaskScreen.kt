package io.github.leoallvez.tasklist.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.ui.FormAppBar

@Composable
fun CreateTaskScreen(
        viewModel: CreateTaskViewModel = hiltViewModel(),
        nav: NavController? = null
    ) {
    var title = remember { mutableStateOf("")}
    var description = remember { mutableStateOf("")}
    val modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp)
    Scaffold(
        topBar = {
            FormAppBar(titleId = R.string.create_task) {
                nav?.navigate(Screen.List.route)
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()){
                    OutlinedTextField(
                        label = { Text(text = "Title") },
                        placeholder = { Text("Title of Task") },
                        value = title.value,
                        onValueChange = {
                            title.value = it
                        },
                        maxLines = 10,
                        modifier =  modifier
                    )
                    OutlinedTextField(
                        label = { Text(text = "Description") },
                        value = description.value,
                        onValueChange = {
                            description.value = it
                        },
                        placeholder = { Text("Description of Task") },
                        maxLines = 10,
                        modifier =  modifier,
                        isError = true,
                    )
                    Spacer(modifier.padding(top = 10.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = modifier,
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            top = 12.dp,
                            end = 20.dp,
                            bottom = 12.dp
                        )
                    ) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Create Task",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                        Text("Create Task")
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun CreateTaskPreview() {
    CreateTaskScreen()
}