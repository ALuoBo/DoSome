package com.aluobo.todo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aluobo.todo.R
import com.aluobo.todo.data.models.Priority
import com.aluobo.todo.data.models.ToDoData
import com.aluobo.todo.data.viewmodel.ToDoViewModel

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var titleEd: EditText
    private lateinit var description: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var saveBt:Button
    private val viewModel by viewModels<ToDoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleEd = view.findViewById(R.id.et_title)
        description = view.findViewById(R.id.et_description)
        prioritySpinner = view.findViewById(R.id.spinner_priority)
        saveBt = view.findViewById(R.id.bt_save)

        saveBt.setOnClickListener {
            insertDataToDb()
        }

    }

    private fun insertDataToDb() {
        val title = titleEd.text.toString()
        val priority = prioritySpinner.selectedItem.toString()
        val description = description.text.toString()
        val validation = verifyDataFromUser(title, description)

        if (validation) {
            val toDoData = ToDoData(
                id = 0,
                title = title,
                priority = parsePriority(priority),
                description = description
            )
            viewModel.insertData(toDoData)
        }
    }

    private fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    private fun parsePriority(priority: String): Priority {
        return when (priority) {
            resources.getStringArray(R.array.priorities)[0] ->
                Priority.HIGH
            resources.getStringArray(R.array.priorities)[1] ->
                Priority.MEDIUM
            resources.getStringArray(R.array.priorities)[2] ->
                Priority.LOW
            else -> Priority.LOW
        }
    }
}