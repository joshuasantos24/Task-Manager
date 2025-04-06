package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.taskmanager.ui.AddTaskScreen
import androidx.compose.runtime.getValue
import com.example.taskmanager.ui.EditTaskScreen
import com.example.taskmanager.ui.TaskListScreen
import com.example.taskmanager.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: TaskViewModel = viewModel()

            NavHost(navController, startDestination = "taskList") {
                composable("taskList") { TaskListScreen(viewModel, navController) }
                composable("addTask") { AddTaskScreen(viewModel, navController) }
                composable("editTask/{taskId}") { backStackEntry ->
                    val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                    val tasks by viewModel.allTasks.observeAsState(emptyList())
                    val task = tasks.find { it.id.toInt() == taskId }

                    task?.let {
                        EditTaskScreen(navController = navController, task = it, viewModel = viewModel)
                    }
                }
            }
        }
    }
}
