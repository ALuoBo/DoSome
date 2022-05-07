package com.aluobo.todo.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aluobo.todo.data.models.ToDoData
import com.aluobo.todo.databinding.RowLayoutBinding

class ToDoListAdapter : ListAdapter<ToDoData, ToDoListAdapter.MyViewHolder>(ToDoDiffUtil) {

    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoData: ToDoData) {
            binding.toDoData = toDoData
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = MyViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            val action =
                ListFragmentDirections.actionListFragmentToUpdtateFragment(currentList[viewHolder.adapterPosition])
            it.findNavController().navigate(action)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}