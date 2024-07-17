package com.prodigyinternship.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prodigyinternship.myapplication.components.AddToDoItemDialog
import com.prodigyinternship.myapplication.components.ToDoItemView
import com.prodigyinternship.myapplication.database.ToDoItemEvent
import com.prodigyinternship.myapplication.database.ToDoItemState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: ToDoItemState,
    onEvent: (ToDoItemEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo List App") },
                actions = {
                    IconButton(onClick = { /* Handle Search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(ToDoItemEvent.ShowDialog) },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
            }
        }
    ) { paddingValues ->
        if (state.isAddingNew) {
            AddToDoItemDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.items) { toDoItem ->
                ToDoItemView(
                    toDoItem = toDoItem,
                    onEvent = onEvent
                )
            }
        }
    }
}
