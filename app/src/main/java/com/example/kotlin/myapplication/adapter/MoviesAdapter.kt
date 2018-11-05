package com.example.kotlin.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.listeners.MovieItemListener
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MoviesAdapter(
    context: Context,
    private var movieItemListener: MovieItemListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var movies = emptyList<MovieEntity>() // Cached copy of words

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieItemName: TextView = itemView.movieName
        val movieItemAvatar: ImageView = itemView.movieAvatar
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MoviesAdapter.MoviesViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val current = movies[position]
        holder.movieItemName.text = current.title
        if (current.poster.equals("", true)) {
        } else {
            Picasso.get().load(current.poster).into(holder.movieItemAvatar)

        }
        holder.itemView.setOnClickListener { movieItemListener.onItemClicked(movie = current) }

    }


    internal fun setMovies(movies: List<MovieEntity>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}