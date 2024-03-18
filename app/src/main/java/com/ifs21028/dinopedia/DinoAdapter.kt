package com.ifs21028.dinopedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DinoAdapter(private val dinoList: ArrayList<Dino>) :
    RecyclerView.Adapter<DinoAdapter.DinoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DinoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_dino, parent, false)
        return DinoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) {
        val dino = dinoList[position]
        holder.bind(dino)
    }

    override fun getItemCount(): Int {
        return dinoList.size
    }

    inner class DinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.ivItemDino)
        private val descTextView: TextView = itemView.findViewById(R.id.ivItemFamili)

        fun bind(dino: Dino) {
            nameTextView.text = dino.name
            descTextView.text = dino.description
        }
    }
}
