package com.siddharthsinghbaghel.swipedeletefirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnStart:Button  = findViewById(R.id.btnWelcome)


        btnStart.setOnClickListener{

            val intent = Intent(this,ListActivity::class.java)
            startActivity(intent);
            finish()
        }


    }
}