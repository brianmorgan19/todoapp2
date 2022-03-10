package com.example.rcview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList = arrayListOf<ToDo>()
        val rvAdapter = ToDoAdapter(todoList)

        /** add dialog fun **/
        fun addDialog(){
            val inflater = LayoutInflater.from(this)
            val v = inflater.inflate(R.layout.add_dialog, null)

            val addDialog = AlertDialog.Builder(this)
            /** set view **/

            addDialog.setPositiveButton("ДОБАВИТЬ") { dialog, i ->
                val title = v.findViewById<EditText>(R.id.todo_title).text.toString()
                if(title.isNotEmpty()){
                    todoList.add(ToDo(title, false))
                    rvAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
                else{
                    val innerAlertDialog = AlertDialog.Builder(this)
                    innerAlertDialog
                        .setTitle("Поле не заполнено.")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("Пожалуйста, введите задание.")
                        .setPositiveButton("OK"){ dialog, i ->
                            dialog.dismiss()
                        }
                    innerAlertDialog.create()
                    innerAlertDialog.show()
                }
            }

            addDialog.setNegativeButton("НАЗАД") { dialog, i ->
                dialog.cancel()
            }

            addDialog.setView(v)

            addDialog.create()
            addDialog.show()
        }

        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        button2.setOnClickListener {
                addDialog()
        }

        button3.setOnClickListener {
            if(todoList.isNotEmpty()){
                rvAdapter.delete(todoList.size - 1)
        }
        }

    }

}