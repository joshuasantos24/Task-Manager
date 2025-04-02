package com.example.taskmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodel.TaskViewModel

@Composable
fun AddTaskScreen(viewModel: TaskViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })

        Button(
            onClick = {
                viewModel.insertTask(Task(title = title, description = description, priority = 2))
                navController.popBackStack()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Guardar")
        }
    }
}
