package com.aluobo.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aluobo.todo.data.ToDoDatabase
import com.aluobo.todo.data.models.ToDoData
import com.aluobo.todo.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getInstance(application).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)
    val getAllData: StateFlow<List<ToDoData>> = repository.getAllData.stateIn(
        viewModelScope, SharingStarted.Eagerly, emptyList()
    )
    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteData(toDoData: ToDoData){
        viewModelScope.launch {
            repository.deleteData(toDoData)
        }
    }
}