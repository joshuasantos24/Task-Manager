package com.example.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks") //  Specify the table name if needed
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Mark your primary key, autogenerate if appropriate
    val title: String,
    val description: String,
    val priority: Int,
    val createdAt: Long = System.currentTimeMillis(),
    var lastUpdated: Long = System.currentTimeMillis()
)