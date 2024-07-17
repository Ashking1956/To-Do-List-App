package com.prodigyinternship.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1)

abstract class toDoItemDatabase : RoomDatabase() {
    abstract val dao: ToDoItemDAO
}