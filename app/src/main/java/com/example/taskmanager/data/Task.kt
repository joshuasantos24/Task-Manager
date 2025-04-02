package com.example.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Int, // 1 = Baja, 2 = Media, 3 = Alta
    val dueDate: Long // Fecha en formato timestamp
)