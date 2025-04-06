package com.example.taskmanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(viewModel: TaskViewModel, navController: NavController) {
    val taskList by viewModel.allTasks.observeAsState(listOf())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Tareas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTask") }) {
                Text("+")
            }
        }
    ) { contentPadding ->  // Captura el padding proporcionado por Scaffold
        LazyColumn(
            contentPadding = contentPadding, // Usa el padding aquí
            modifier = Modifier.fillMaxSize()
        ) {
            items(taskList) { task ->
                TaskItem(task, viewModel, navController)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro que deseas eliminar esta tarea?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteTask(task)
                    showDialog = false
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = task.title, style = MaterialTheme.typography.h6)
                Row {
                    TextButton(onClick = {
                        navController.navigate("editTask/${task.id}")
                    }) {
                        Text("Editar")
                    }
                    TextButton(onClick = {
                        showDialog = true
                    }) {
                        Text("Eliminar", color = MaterialTheme.colors.error)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description)
        }
    }
}

