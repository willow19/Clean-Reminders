package com.example.reminderapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        val className = MainActivity::class.java.simpleName
    }

    private lateinit var mTasksList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mTasksList = findViewById(R.id.layout_linear)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            addView(view)
        }

        if (savedInstanceState != null) {
            val tasksArray = savedInstanceState.getStringArray("active_tasks")
            if (tasksArray != null) {
                for (task in tasksArray) {
                    addView(mTasksList, task)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tasksList = mutableListOf<String>()
        for (index in 0 until mTasksList.childCount) {
            val taskLayout = mTasksList.getChildAt(index) as LinearLayout
            val editText = taskLayout.getChildAt(1) as EditText
            tasksList.add(editText.text.toString())
        }
        outState.putStringArray("active_tasks", tasksList.toTypedArray())
    }

    private fun onCheckboxClicked(view: View) {
        Log.d(className, view.toString())
        Log.d(className, view.rootView.toString())
        mTasksList.removeView(view.parent as LinearLayout)
    }

    fun addView(view: View, text: String = getString(R.string.text_placeholder)) {
        val checkBox = CheckBox(view.context)
        checkBox.setOnClickListener { cbView -> onCheckboxClicked(cbView) }
        val editText = getEditText(view.context, text)
        val linearLayout = LinearLayout(view.context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.addView(checkBox)
        linearLayout.addView(editText)
        mTasksList.addView(linearLayout)
    }

    private fun getEditText(context: Context, text: String): View {
        val editText = EditText(context)
        editText.setText(text)
        editText.maxLines = 1
        editText.setLines(1)
        editText.isSingleLine = true
        return editText
    }
}