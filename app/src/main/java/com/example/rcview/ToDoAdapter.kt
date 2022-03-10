package com.example.rcview

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val currItem = todos[position]
        holder.itemView.apply {
            id_todo.text = todos[position].title
            strikeThroughLine(id_todo, currItem.isChecked)
            id_checkbox.setOnCheckedChangeListener { _, b ->
                strikeThroughLine(id_todo, b)
                currItem.isChecked != currItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun strikeThroughLine(title: TextView, isChecked:Boolean){
        if(isChecked){
            title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun add(item:ToDo){
        todos.add(item)
        notifyItemInserted(todos.size - 1)
    }

    fun delete(index: Int){
        todos.removeAt(index)
        notifyDataSetChanged()
    }

}