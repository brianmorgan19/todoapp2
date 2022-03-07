package com.example.rcview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = arrayListOf<ToDo>()

        val rvAdapter = ToDoAdapter(todoList)

        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        button2.setOnClickListener {
            if(editText.text.isEmpty()){
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Пустое поле")
                    .setIcon(R.drawable.ic_warning)
                    .setMessage("Поле не должно быть пустым.")
                    .setCancelable(true)
                dialog.show()
            }
            else{
                val title = editText.text.toString()
                val todo = ToDo(title, false)
                todoList.add(todo)
                rvAdapter.notifyItemInserted(todoList.size - 1)

                val edtext = editText.text
                edtext.clear()
        }
        }


    }
}