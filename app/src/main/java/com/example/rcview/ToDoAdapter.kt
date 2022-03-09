package com.example.rcview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoAdapter(
    val todos: MutableList<ToDo>
):RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            id_todo.text = todos[position].title
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun delete(index: Int){
        todos.removeAt(index)
        notifyDataSetChanged()
    }

}