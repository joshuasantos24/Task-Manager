package com.example.taskmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(viewModel: TaskViewModel, navController: NavController) {
    val taskList by viewModel.allTasks.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas", fontWeight = FontWeight.Bold) },
                backgroundColor = MaterialTheme.colors.primarySurface
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTask") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Tarea")
            }
        },
        content = { contentPadding ->
            LazyColumn(
                contentPadding = contentPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                items(taskList) { task ->
                    TaskItem(task, viewModel, navController)
                }
            }
        }
    )
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

    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(task.createdAt))
    val lastUpdatedDate = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date(task.lastUpdated))


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = task.description)
                }

                PriorityBadge(priority = task.priority)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    navController.navigate("editTask/${task.id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }

                IconButton(onClick = { showDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colors.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                Text(text = "Creado: $formattedDate", style = MaterialTheme.typography.caption)
                Text("Última actualización: $lastUpdatedDate", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun PriorityBadge(priority: Int) {
    val (color, label) = when (priority) {
        3 -> Color.Red to "Alta"
        2 -> Color(0xFFFFA500) to "Media"
        else -> Color.Green to "Baja"
    }

    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.2f), shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold
        )
    }
}


