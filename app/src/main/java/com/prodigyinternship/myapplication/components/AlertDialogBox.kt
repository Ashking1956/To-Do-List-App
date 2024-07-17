package com.prodigyinternship.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.prodigyinternship.myapplication.database.ToDoItemEvent
import com.prodigyinternship.myapplication.database.ToDoItemState


@Composable
fun AddToDoItemDialog(
    state: ToDoItemState,
    onEvent: (ToDoItemEvent) -> Unit
) {
    var title by remember { mutableStateOf(state.title) }
    var description by remember { mutableStateOf(state.description) }
    var priority by remember { mutableStateOf(state.priority.toString()) }

    if (state.isAddingNew) {
        title = state.title
        description = state.description
        priority = state.priority.toString()
    }

    AlertDialog(
        onDismissRequest = { onEvent(ToDoItemEvent.HideDialog) },
        title = { Text(text = if (state.isAddingNew) "Add To-Do Item" else "Edit To-Do Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                OutlinedTextField(
                    value = priority,
                    onValueChange = { priority = it },
                    label = { Text("Priority") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(ToDoItemEvent.SetTitle(title))
                    onEvent(ToDoItemEvent.SetDescription(description))
                    onEvent(ToDoItemEvent.SetPriority(priority.toIntOrNull() ?: 0))
                    onEvent(ToDoItemEvent.SaveToDoItem)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(
                onClick = { onEvent(ToDoItemEvent.HideDialog) }
            ) {
                Text("Cancel")
            }
        }
    )
}
