package io.github.leoallvez.tasklist.ui.create

import android.widget.Toast
import androidx.annotation.StringRes
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
import androidx.navigation.NavController
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.Screen
import io.github.leoallvez.tasklist.ui.FormAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.github.leoallvez.tasklist.model.Task

@Composable
fun CreateTaskScreen(
        viewModel: CreateTaskViewModel?,
        nav: NavController?
    ) {

    Scaffold(
        topBar = {
            FormAppBar(titleId = R.string.create_task) {
                nav?.navigate(Screen.List.route)
            }
        },
        content = {
            val context = LocalContext.current
            TaskForm { title, description ->
                val task = Task(title = title, description = description)
                viewModel?.createTask(task)
                Toast.makeText(
                    context,
                    "Task Created",
                    Toast.LENGTH_SHORT
                ).show()
                nav?.navigate(Screen.List.route)
            }
        }
    )
}

@Composable
fun TaskForm(saveTask: (title: String, description: String) -> Unit) {

    var submited by remember { mutableStateOf (value = false) }
    var title by remember { mutableStateOf(value = "")}
    var description by remember { mutableStateOf(value = "")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()){

            FormTextField(
                textValue = title,
                labelId = R.string.title,
                onChance = {
                    title = it
                },
                isError = title.isEmpty() && submited
            )

            FormTextField(
                textValue = description,
                labelId = R.string.description,
                onChance = {
                    description = it
                },
                isError = description.isEmpty() && submited
            )

            FormButton(textId = R.string.create_task) {
                submited = true
                val isValid = title.isEmpty().not() && description.isEmpty().not()
                if(isValid) {
                    saveTask(title, description)
                }
            }
        }
    }
}

@Composable
fun FormTextField(
    textValue: String,
    @StringRes labelId: Int,
    onChance: (value: String) -> Unit,
    isError: Boolean = true,
) {
    OutlinedTextField(
        label = { Text(stringResource(id = labelId)) },
        value = textValue,
        onValueChange = { onChance.invoke(it) },
        maxLines = 10,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        isError = isError,
    )
}

@Composable
fun FormButton(@StringRes textId: Int, onClick: () -> Unit) {

    Spacer(modifier = Modifier.padding(top = 10.dp))

    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Icon(
            Icons.Filled.AddCircle,
            contentDescription = stringResource(id = textId),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = stringResource(id = textId))
    }
}

@Preview
@Composable
fun CreateTaskPreview() {
    CreateTaskScreen(viewModel = null, nav = null)
}