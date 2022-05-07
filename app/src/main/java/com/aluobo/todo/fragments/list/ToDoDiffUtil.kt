package com.aluobo.todo.fragments.list

import androidx.recyclerview.widget.DiffUtil
import com.aluobo.todo.data.models.ToDoData

object ToDoDiffUtil: DiffUtil.ItemCallback<ToDoData>() {
    override fun areItemsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoData, newItem: ToDoData): Boolean {
        return oldItem == newItem
    }
}