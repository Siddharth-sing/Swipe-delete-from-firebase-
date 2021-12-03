package com.siddharthsinghbaghel.swipedeletefirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ListAdapter(options: FirestoreRecyclerOptions<ItemModel>) :
    FirestoreRecyclerAdapter<ItemModel, ListAdapter.ListViewHolder>(options) {

     inner class ListViewHolder(view:View): RecyclerView.ViewHolder(view){

        val txtTitle : TextView = view.findViewById(R.id.txtTitle)
        val txtDesc : TextView = view.findViewById(R.id.txtDesc)
        val txtTs : TextView = view.findViewById(R.id.txtTs)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
        return view
    }

    override fun onBindViewHolder(holder: ListViewHolder, p1: Int, itemModel: ItemModel) {
        holder.txtTitle.text = itemModel.title
        holder.txtDesc.text = itemModel.description
        holder.txtTs.text = itemModel.ts
    }

    fun deleteItem(position : Int)
    {
        snapshots.getSnapshot(position).reference.delete()
    }


}