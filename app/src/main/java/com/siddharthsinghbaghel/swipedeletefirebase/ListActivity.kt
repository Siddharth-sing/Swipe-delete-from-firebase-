package com.siddharthsinghbaghel.swipedeletefirebase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ListActivity : AppCompatActivity() {


    var db  = FirebaseFirestore.getInstance()
    private lateinit var mAdapter : ListAdapter
    private lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

        fabAdd.setOnClickListener{

             val intent = Intent(this,AddItemActivity::class.java)
             startActivity(intent)
         }
        setUpRecyclerView()
        
    }

    private fun setUpRecyclerView() {
        Toast.makeText(this,"Here",LENGTH_LONG).show()
        val query: Query = db.collection("ItemList").orderBy("ts", Query.Direction.DESCENDING).limit(50)
        Toast.makeText(this,"Query == ${query}",LENGTH_LONG).show()
            val options: FirestoreRecyclerOptions<ItemModel> = FirestoreRecyclerOptions.Builder<ItemModel>()
            .setQuery(query, ItemModel::class.java)
            .build()

        mAdapter = ListAdapter(options)
        mAdapter.startListening()
        rv  = findViewById(R.id.rvList)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter



    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",LENGTH_LONG).show()
        mAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mAdapter.stopListening()
    }

}