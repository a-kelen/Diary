package com.diary

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val box =  findViewById<TextView>(R.id.box)
        val textView = findViewById<TextView>(R.id.message)
        textView.setTextColor(Color.GREEN)
        val xview = findViewById<TextView>(R.id.x)
        val yview = findViewById<TextView>(R.id.y)
        val switch = findViewById<Switch>(R.id.switch1)
        val back = findViewById<Button>(R.id.button)
        val rating = findViewById<RatingBar>(R.id.ratingBar)
        val toggle = findViewById<ToggleButton>(R.id.toggleButton2)
        switch.setLinkTextColor(Color.RED)
        textView.textSize = 30f
        textView.text = message
        box.setOnTouchListener() { v, event ->
            if(event.getX() > 0 && event.getY() > 0)
            {
                xview.text = "X: " + event.getX().toInt().toString()
                yview.text = "Y: " + event.getY().toInt().toString()
            } else {
                xview.text = "0"
                yview.text = "0"
            }
            true
        }
        switch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
                textView.setTextColor(Color.RED)
            else
                textView.setTextColor(Color.GREEN)
        }
        back.setOnClickListener(View.OnClickListener {
            finish()
        })
        rating.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                Toast.makeText(this@DisplayMessageActivity, "Rating: " + rating.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
                window.decorView.setBackgroundColor(Color.LTGRAY)
            else
                window.decorView.setBackgroundColor(Color.WHITE)
        }
    }
}
