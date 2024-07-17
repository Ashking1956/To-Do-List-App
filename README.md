# To-Do List App

This is a simple To-Do List application built using Jetpack Compose in Android Studio. The app allows users to create, edit, and manage their to-do items efficiently.

## Features

- **Add To-Do Items**: Easily add new to-do items with a title, description, and priority.
- **Edit To-Do Items**: Edit existing to-do items to update their details.
- **Delete To-Do Items**: Remove to-do items that are no longer needed.
- **Priority-based Ordering**: To-do items are displayed in order of priority.
- **Mark as Done**: Toggle the completion status of to-do items.

## Technologies Used

- **Kotlin**: For app development.
- **Jetpack Compose**: For building the UI.
- **Room**: For database management.
- **StateFlow**: For state management.
- **Coroutines**: For handling asynchronous tasks.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Kotlin 1.5.21 or later
- Gradle 7.0.2 or later

### Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/Ashking1956/To-Do-List-App.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Build and run the project on an emulator or a physical device.

## Usage

1. **Adding a To-Do Item**:
    - Click on the "+" button to open the dialog for adding a new to-do item.
    - Fill in the title, description, and priority, then click "Save".

2. **Editing a To-Do Item**:
    - Click on an existing to-do item to open the dialog for editing.
    - Update the title, description, and priority, then click "Save".

3. **Deleting a To-Do Item**:
    - Swipe left or right on a to-do item to delete it.

4. **Marking a To-Do Item as Done**:
    - Tap on the checkbox next to a to-do item to mark it as done.

## Code Structure

- **data**: Contains data classes and database setup.
    - `TodoItem.kt`: Data class representing a to-do item.
    - `ToDoItemDAO.kt`: Data Access Object (DAO) interface for accessing the database.
    - `ToDoItemDatabase.kt`: Room database class.
- **ui**: Contains the UI components and screens.
    - `MainActivity.kt`: Main activity hosting the composable functions.
    - `MainScreen.kt`: Composable function for the main screen.
    - `AddToDoItemDialog.kt`: Composable function for adding a new to-do item.
    - `EditToDoItemDialog.kt`: Composable function for editing an existing to-do item.
    - `ToDoItemView.kt`: Composable function for displaying a to-do item.
- **viewmodel**: Contains the ViewModel for managing UI-related data.
    - `MainViewModel.kt`: ViewModel for the main screen.
    - `ToDoItemEvent.kt`: Sealed class representing different UI events.
    - `ToDoItemState.kt`: Data class representing the state of the UI.
