package com.siddharthsinghbaghel.swipedeletefirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
      
    
         val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

         fabAdd.setOnClickListener{

         }
        
    }
}