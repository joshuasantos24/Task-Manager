package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.taskmanager.ui.AddTaskScreen
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
            }
        }
    }
}
