package com.prodigyinternship.myapplication.database

data class ToDoItemState(
    val title: String = "",
    val description: String = "",
    val priority: Int = 0,
    val isDone: Boolean = false,
    val isAddingNew: Boolean = false,
    val items: List<TodoItem> = emptyList(),
    val isEditing: Boolean = false
)
