package com.aluobo.todo.data.repository

import androidx.lifecycle.LiveData
import com.aluobo.todo.data.ToDoDao
import com.aluobo.todo.data.models.ToDoData
import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: Flow<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) = toDoDao.insertData(toDoData = toDoData)

    suspend fun updateData(toDoData: ToDoData) = toDoDao.updateData(toDoData = toDoData)

    suspend fun deleteData(toDoData: ToDoData) = toDoDao.deleteData(toDoData = toDoData)
}