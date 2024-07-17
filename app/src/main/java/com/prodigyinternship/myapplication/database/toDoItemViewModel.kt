package com.prodigyinternship.myapplication.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val dao: ToDoItemDAO
) : ViewModel() {

    private val _state = MutableStateFlow(ToDoItemState())
    val state: StateFlow<ToDoItemState> = _state

    init {
        viewModelScope.launch {
            dao.getToDoItemsByPriority().collect { items ->
                _state.value = _state.value.copy(items = items)
            }
        }
    }

    fun onEvent(event: ToDoItemEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is ToDoItemEvent.DeleteToDoItem -> {
                        dao.deleteToDoItem(event.todoItem)
                    }

                    ToDoItemEvent.HideDialog -> {
                        _state.value = _state.value.copy(isAddingNew = false)
                    }

                    ToDoItemEvent.SaveToDoItem -> {
                        val toDoItem = TodoItem(
                            id = 0, // Ensure ID is 0 for new items
                            title = _state.value.title,
                            description = _state.value.description,
                            priority = _state.value.priority,
                            isDone = _state.value.isDone
                        )
                        if (_state.value.items.any { it.id == toDoItem.id }) {
                            dao.updateToDoItem(toDoItem)
                        } else {
                            dao.addToDoItem(toDoItem)
                        }
                        _state.value = _state.value.copy(
                            isAddingNew = false,
                            title = "",
                            description = "",
                            priority = 0,
                            isDone = false
                        )
                    }

                    is ToDoItemEvent.SetDescription -> {
                        _state.value = _state.value.copy(description = event.description)
                    }

                    is ToDoItemEvent.SetPriority -> {
                        _state.value = _state.value.copy(priority = event.priority)
                    }

                    is ToDoItemEvent.SetTitle -> {
                        _state.value = _state.value.copy(title = event.title)
                    }
                    is ToDoItemEvent.ShowEditDialog->{
                        _state.value = _state.value.copy(isEditing = true)
                    }
                    is ToDoItemEvent.HideEditDialog->{
                        _state.value = _state.value.copy(isEditing = false)
                    }

                    is ToDoItemEvent.EditToDoItem -> {
                        // Update the state to reflect editing mode
                        _state.value = _state.value.copy(
                            isAddingNew = true, // Change to true to indicate editing mode
                            title = event.todoItem.title,
                            description = event.todoItem.description,
                            priority = event.todoItem.priority,
                            isDone = event.todoItem.isDone
                        )
                    }

                    ToDoItemEvent.ShowDialog -> {
                        _state.value = _state.value.copy(isAddingNew = true)
                    }

                    is ToDoItemEvent.ToggleDone -> {
                        val updatedItem = event.todoItem.copy(isDone = !event.todoItem.isDone)
                        dao.updateToDoItem(updatedItem)
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions, e.g., log or display error message
                e.printStackTrace()
            }
        }
    }
}
