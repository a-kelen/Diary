package com.diary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }
    lateinit var edit_message: EditText
    lateinit var edit_button: EditText
    lateinit var long_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit_message= findViewById<EditText>(R.id.edit_message)
        edit_button= findViewById<EditText>(R.id.edit_button)
        long_button= findViewById<Button>(R.id.longButton)

        long_button.setOnLongClickListener(View.OnLongClickListener {
            longButton()
        })
        edit_message.setOnClickListener(View.OnClickListener {
            edit_message.text.clear()
        })
        edit_button.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                long_button.text = edit_button.text
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
    fun sendMessage(view: View) {
        val message: String = edit_message.text.toString()
        val intent: Intent = Intent(this, DisplayMessageActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
     fun longButton() : Boolean  {
        Toast.makeText(this@MainActivity, edit_button.text, Toast.LENGTH_SHORT).show()
         return true;
    }
    fun toCreateButton(view: View) {
        val intent: Intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent);
    }
}
