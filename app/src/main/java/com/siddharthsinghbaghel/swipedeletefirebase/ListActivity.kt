package com.siddharthsinghbaghel.swipedeletefirebase

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class ListActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)



         val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

         fabAdd.setOnClickListener{

             val customDialog = Dialog(this)
             customDialog.setContentView(R.layout.dialog_add)
             customDialog.show()
             val window: Window? = customDialog.window
             window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)

         }
        
    }

    fun save(view: View) {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        addItemToFirebase()
        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addItemToFirebase() {
        TODO("Not yet implemented")
    }

    fun cancel(view: View) {
        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)
        finish()


    }

}