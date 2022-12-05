package com.example.notes.ui.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.model.Note
import com.example.notes.databinding.ItemNoteBinding
import com.squareup.picasso.Picasso

class NoteAdapter(private val notes:List<Note>, private val listener:(Note) ->Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val binding  = ItemNoteBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.setOnClickListener {
            listener(note)
        }

        Picasso.get().load(note.image).into(holder.binding.imgNota)

    }

    override fun getItemCount(): Int {
        return notes.size
    }
}