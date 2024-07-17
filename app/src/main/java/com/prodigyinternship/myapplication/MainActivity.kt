package com.prodigyinternship.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.prodigyinternship.myapplication.database.MainViewModel
import com.prodigyinternship.myapplication.database.ToDoItemDAO
import com.prodigyinternship.myapplication.database.toDoItemDatabase
import com.prodigyinternship.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            toDoItemDatabase::class.java,
            "toDoItem.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                ) {
                    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(db.dao))
                    MainScreen(
                        state = viewModel.state.collectAsState().value,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}

class MainViewModelFactory(
    private val dao: ToDoItemDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
