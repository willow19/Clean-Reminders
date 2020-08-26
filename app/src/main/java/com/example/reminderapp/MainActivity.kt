package com.example.reminderapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        val className = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            addView(view)
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

    private fun onCheckboxClicked(view: View) {
        Log.d(className, view.toString())
        Log.d(className, view.rootView.toString())
        findViewById<LinearLayout>(R.id.layout_linear).removeView(view.parent as LinearLayout)
    }

    fun addView(view: View) {
        val checkBox = CheckBox(view.context)
        checkBox.setOnClickListener { cbView -> onCheckboxClicked(cbView) }
        val editText = getEditText(view.context)
        val linearLayout = LinearLayout(view.context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.addView(checkBox)
        linearLayout.addView(editText)
        findViewById<LinearLayout>(R.id.layout_linear).addView(linearLayout)
    }

    private fun getEditText(context: Context): View {
        val editText = EditText(context)
        editText.setText(getString(R.string.text_placeholder))
        editText.maxLines = 1
        editText.setLines(1)
        editText.isSingleLine = true
        return editText
    }
}