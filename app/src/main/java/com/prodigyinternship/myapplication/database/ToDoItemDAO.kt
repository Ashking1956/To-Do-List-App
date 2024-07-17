package com.prodigyinternship.myapplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoItemDAO {
    @Insert
    suspend fun addToDoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteToDoItem(todoItem: TodoItem)

    @Query("SELECT * FROM TodoItem ORDER BY priority DESC")
    fun getToDoItemsByPriority(): Flow<List<TodoItem>>

    @Update
    suspend fun updateToDoItem(todoItem: TodoItem)
}