package io.github.leoallvez.tasklist.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.leoallvez.tasklist.R
import io.github.leoallvez.tasklist.model.Task
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

@Composable
fun TaskForm(
    task: Task = Task(),
    isEditing: Boolean,
    onSave: (task: Task) -> Unit
) {
    var title by remember { mutableStateOf(value = task.title) }
    var description by remember { mutableStateOf(value = task.description) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()){

            FormTextField(
                fieldValue = title,
                labelId = R.string.title,
                onChance = {
                    title = it
                },
            )

            FormTextField(
                fieldValue = description,
                labelId = R.string.description,
                onChance = {
                    description = it
                },
            )

            FormButton(
                isEditing = isEditing,
                enabled = title.isNotEmpty() && description.isNotEmpty()
            ) {
                task.title = title
                task.description = description
                if(task.isFilled()) {
                    onSave(task)
                }
            }
        }
    }
}

@Composable
fun  FormTextField(
    fieldValue: String,
    @StringRes labelId: Int,
    onChance: (value: String) -> Unit,
) {
    Column {
        OutlinedTextField(
            label = { Text(stringResource(id = labelId)) },
            value = fieldValue,
            onValueChange = {
                onChance.invoke(it)
            },
            maxLines = 10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
        )
    }
}

@Composable
fun FormButton(
    isEditing: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val text = stringResource(
        id = if(isEditing) R.string.edit_task else R.string.create_task
    )
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
        ),
        enabled = enabled
    ) {
        Icon(
            if(isEditing) Icons.Filled.Edit else Icons.Filled.AddCircle,
            contentDescription = text,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text)
    }
}

@Composable
fun LoadingCentred() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
