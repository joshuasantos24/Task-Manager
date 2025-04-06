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
fun EditTaskScreen(
    navController: NavController,
    task: Task,
    viewModel: TaskViewModel
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var priority by remember { mutableStateOf(task.priority.toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Editar Tarea", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = priority,
            onValueChange = { priority = it },
            label = { Text("Prioridad (1-3)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedTask = task.copy(
                title = title,
                description = description,
                priority = priority.toIntOrNull() ?: 1
            )
            viewModel.updateTask(updatedTask)
            navController.popBackStack() // Regresa a la pantalla anterior
        }) {
            Text("Guardar Cambios")
        }
    }
}
