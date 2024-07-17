package com.prodigyinternship.myapplication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prodigyinternship.myapplication.database.ToDoItemEvent
import com.prodigyinternship.myapplication.database.TodoItem

val toDoItem = TodoItem(
    id = 0,
    title = "Title",
    description = "Rohit Gurunath Sharma (born 30 April 1987) is an Indian international cricketer who currently plays for and captains the India national cricket team in Test and One Day International (ODI) matches. He formerly captained the team in Twenty20 International (T20I) matches until his retirement after India's win in 2024 ICC Men's T20 World Cup.[4] The right-handed batsman, who is considered one of the best batsmen of his generation and one of the greatest opening batters of all time,[5] is known for his timing, elegance, six-hitting abilities and leadership skills.\n" +
            "\n" +
            "He holds several batting records which famously include most runs in T20 Internationals, most sixes in international cricket,[a] most double centuries in ODI cricket (3), most centuries at Cricket World Cups (7) and joint most hundreds in Twenty20 Internationals (5). He is the first player to score 5 T20I centuries.[7] He also holds the world record for the highest individual score (264) in a One Day International (ODI) match and is the only player to have scored three double-centuries in ODIs and also holds the record for scoring most hundreds (five) in a single Cricket World Cup, for which he won the ICC Men's ODI Cricketer of the Year award in 2019. He is the only player to win 50 matches as captain in T20Is.[8]",
    priority = 1,
    isDone = false
)

@Composable
fun ToDoItemView(
    toDoItem: TodoItem,
    onEvent: (ToDoItemEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = toDoItem.isDone,
                onCheckedChange = { onEvent(ToDoItemEvent.ToggleDone(toDoItem)) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = toDoItem.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = if (toDoItem.description.length < 20) toDoItem.description else "${
                        toDoItem.description.substring(
                            0,
                            20
                        )
                    }...",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                )
            }
            Row(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onEvent(ToDoItemEvent.EditToDoItem(toDoItem)) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }

                IconButton(onClick = { onEvent(ToDoItemEvent.DeleteToDoItem(toDoItem)) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewToDoItemView() {
    ToDoItemView(toDoItem) {}


}


