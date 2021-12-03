package com.siddharthsinghbaghel.swipedeletefirebase
import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

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
        val query: Query = db.collection("ItemList").orderBy("ts", Query.Direction.DESCENDING)
        Toast.makeText(this,"Query == ${query}",LENGTH_LONG).show()
            val options: FirestoreRecyclerOptions<ItemModel> = FirestoreRecyclerOptions.Builder<ItemModel>()
            .setQuery(query, ItemModel::class.java)
            .build()

        mAdapter = ListAdapter(options)
        mAdapter.startListening()
        rv  = findViewById(R.id.rvList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter


        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mAdapter.deleteItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            this@ListActivity,
                            android.R.color.holo_red_light
                        )
                    )
                    .addActionIcon(R.drawable.ic_baseline_delete_sweep_24)
                    .addSwipeRightLabel("Deleting the Item")
                    .addSwipeLeftLabel("Deleting the Item")
                    .setSwipeRightLabelColor(R.color.white)
                    .setSwipeLeftLabelColor(R.color.white)
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)


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