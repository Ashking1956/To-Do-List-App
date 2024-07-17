package com.prodigyinternship.myapplication.database

sealed interface ToDoItemEvent {
    data object SaveToDoItem : ToDoItemEvent
    data class SetTitle(val title: String) : ToDoItemEvent
    data class SetDescription(val description: String) : ToDoItemEvent
    data class SetPriority(val priority: Int) : ToDoItemEvent
    data class ToggleDone(val todoItem: TodoItem) : ToDoItemEvent
    data object ShowDialog : ToDoItemEvent
    data object HideDialog : ToDoItemEvent
    data object ShowEditDialog : ToDoItemEvent
    data object HideEditDialog : ToDoItemEvent
    data class DeleteToDoItem(val todoItem: TodoItem) : ToDoItemEvent
    data class EditToDoItem(val todoItem: TodoItem) : ToDoItemEvent
}


/*
* val title: String,
    val description: String,
    val priority: Int,
    val isDone: Boolean
* */