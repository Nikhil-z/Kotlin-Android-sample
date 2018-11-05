package com.example.kotlin.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.listeners.NoteItemsListener
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_items_note.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class NotesAdapter internal constructor(
    context: Context, private val noteItemsListener: NoteItemsListener
) : RecyclerView.Adapter<NotesAdapter.MovieViewHolder>() {

    /*Lets do some memory intensive operations with CoroutineScope*/
    var parentJob = Job()
    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    val scope = CoroutineScope(coroutineContext)

    private lateinit var context: Context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes: MutableList<NotesEntity> = ArrayList() // Cached copy of words

    /*For delete operation*/
    private var deleted: Boolean = false
    private lateinit var deletedNote: NotesEntity

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.name
        val description: TextView = itemView.description
        val author: TextView = itemView.price
        val delete: ImageView = itemView.delete_icon
        val thumbnail: ImageView = itemView.thumbnail
        var viewBackground: RelativeLayout = itemView.view_background
        var viewForeground: RelativeLayout = itemView.view_foreground
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NotesAdapter.MovieViewHolder {
        val itemView = inflater.inflate(R.layout.list_items_note, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NotesAdapter.MovieViewHolder, position: Int) {
        val current = notes[position]

        holder.title.text = current.title
        holder.description.text = current.description
        holder.author.text = current.author
        holder.itemView.setOnClickListener { noteItemsListener.onItemClicked(current) }
        Picasso.get().load("https://picsum.photos/300/30$position/?random").into(holder.thumbnail)

    }


    internal fun setNotes(movies: List<NotesEntity>) {

        scope.launch {

            if (deleted) {
                deleted = false
                notifyItemRemoved(notes.indexOf(deletedNote))
                notes.removeAt(notes.indexOf(deletedNote))
            } else {
                movies.forEach {
                    if (notes.indexOf(it) == (-1)) {
                        notes.add(it)
                        notifyItemInserted(notes.size)
                    }
                }
            }

        }

    }

    internal fun delete(position: Int) {
        deleted = true
        deletedNote = notes[position]
        noteItemsListener.onItemDeleted(notes[position])
    }


}