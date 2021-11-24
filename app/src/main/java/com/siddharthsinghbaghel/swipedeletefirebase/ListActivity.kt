package com.siddharthsinghbaghel.swipedeletefirebase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

        fabAdd.setOnClickListener{

             val intent = Intent(this,AddItemActivity::class.java)
             startActivity(intent)
         }
        
    }

}