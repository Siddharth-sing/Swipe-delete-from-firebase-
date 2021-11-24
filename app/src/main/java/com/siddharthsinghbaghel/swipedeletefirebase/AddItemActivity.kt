package com.siddharthsinghbaghel.swipedeletefirebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.firestore.FirebaseFirestore

class AddItemActivity : AppCompatActivity() {

    private var titleText : String = ""
    private var descText : String = ""
    private var timeStamp : String = ""
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val btnSave: Button = findViewById(R.id.btnSave)
        val btnCancel: Button = findViewById(R.id.btnCancel)

        btnSave.setOnClickListener{

            val txtTitle : TextView = findViewById(R.id.titleI)
            val txtDesc : TextView = findViewById(R.id.descI)
            val tsLong = System.currentTimeMillis() / 1000

            timeStamp = tsLong.toString()
            titleText = txtTitle.text.toString()
            descText = txtDesc.text.toString()



            saveInFirestore(timeStamp)
        }
        btnCancel.setOnClickListener{
            Toast.makeText(this, "In cancel Button", LENGTH_SHORT).show()
            val intent = Intent(this,ListActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    private fun saveInFirestore(timeStamp: String) {

        val newItem = ItemModel(titleText,descText,timeStamp)
        db.collection("ItemList").document(timeStamp).set(newItem)
            .addOnSuccessListener {
                Toast.makeText(this, "uploaded Successfully", LENGTH_LONG).show()
                Log.d(TAG, "DocumentSnapshot successfully written!")
            }.addOnFailureListener {
                    e -> Log.e(TAG, "Error writing document", e)
                    print("Error = $e")
            }

        Toast.makeText(this,"${newItem.title} saved in database",LENGTH_LONG).show()
        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)
        finish()
    }


}