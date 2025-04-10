package com.example.taskmanager.ui.components



import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PriorityOption(
    label: String,
    value: Int,
    selected: Int,
    onSelect: (Int) -> Unit
) {
    val color = when (value) {
        3 -> Color.Red
        2 -> Color(0xFFFFA500)
        else -> Color.Green
    }

    OutlinedButton(
        onClick = { onSelect(value) },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = if (value == selected) color.copy(alpha = 0.2f) else Color.Transparent
        ),
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(
            label,
            color = color,
            fontWeight = if (value == selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}