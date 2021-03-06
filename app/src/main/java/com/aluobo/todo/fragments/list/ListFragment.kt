package com.aluobo.todo.fragments.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aluobo.todo.R
import com.aluobo.todo.data.models.ToDoData
import com.aluobo.todo.data.viewmodel.ToDoViewModel
import com.aluobo.todo.databinding.FragmentListBinding
import com.aluobo.todo.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment(R.layout.fragment_list) {

    private val toDoListAdapter: ToDoListAdapter by lazy { ToDoListAdapter() }
    private val toDoViewModel by viewModels<ToDoViewModel>()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        binding.recyclerView.apply {
            adapter = toDoListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launchWhenResumed {
            toDoViewModel.getAllData.collect{
                toDoListAdapter.submitList(ArrayList(it))
                Toast.makeText(requireContext(), "7777", Toast.LENGTH_SHORT).show()
            }
        }

        // set recycler view  swipe to delete
        swipeToDelete(binding.recyclerView)
        hideKeyboard(requireActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val itemToDelete = toDoListAdapter.currentList[viewHolder.adapterPosition]
                    toDoViewModel.deleteData(itemToDelete)
                    restoreDeleteData(viewHolder.itemView, itemToDelete)
                }

            }
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(recyclerView)
    }

    private fun restoreDeleteData(itemView: View, deleteData: ToDoData) {
        val snackBar = Snackbar.make(itemView, "????????? ${deleteData.title}", Snackbar.LENGTH_LONG)
        snackBar.setAction("??????") {
            toDoViewModel.insertData(deleteData)
        }
        snackBar.show()
    }

}