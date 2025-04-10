



package com.example.taskmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodel.TaskViewModel
import com.example.taskmanager.ui.components.PriorityOption

@Composable
fun EditTaskScreen(
    navController: NavController,
    task: Task,
    viewModel: TaskViewModel
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var priority by remember { mutableStateOf(task.priority) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Tarea", fontWeight = FontWeight.Bold) },
                backgroundColor = MaterialTheme.colors.primarySurface
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Prioridad", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Medium)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PriorityOption("Baja", 1, priority) { priority = it }
                PriorityOption("Media", 2, priority) { priority = it }
                PriorityOption("Alta", 3, priority) { priority = it }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val updatedTask = task.copy(
                        title = title,
                        description = description,
                        priority = priority
                    )
                    viewModel.updateTask(updatedTask)
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}