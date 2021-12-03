package com.siddharthsinghbaghel.swipedeletefirebase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ListActivity : AppCompatActivity() {


    private val db  = FirebaseFirestore.getInstance()
    lateinit var adapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        setUpRecyclerView()

        val fabAdd : FloatingActionButton = findViewById(R.id.fabAdd)

        fabAdd.setOnClickListener{

             val intent = Intent(this,AddItemActivity::class.java)
             startActivity(intent)
         }
        
    }

    private fun setUpRecyclerView() {
        Toast.makeText(this,"Here",LENGTH_LONG).show()
        val query: Query = db.collection("ItemList").orderBy("ts", Query.Direction.DESCENDING)
        print("Query == $query")
            val options: FirestoreRecyclerOptions<ItemModel> = FirestoreRecyclerOptions.Builder<ItemModel>()
            .setQuery(query, ItemModel::class.java)
            .build()

        adapter = ListAdapter(options)
        val rv : RecyclerView = findViewById(R.id.rvList)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}