package com.siddharthsinghbaghel.swipedeletefirebase

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter

class ListAdapter: FirestoreRecyclerAdapter {

    public class ListViewHolder(view:View): RecyclerView.ViewHolder(view){

        val txtTitle : TextView = view.findViewById(R.id.txtTitle)
        val txtDesc : TextView = view.findViewById(R.id.txtDesc)


    }



}