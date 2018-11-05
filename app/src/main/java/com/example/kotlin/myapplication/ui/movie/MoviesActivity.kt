package com.example.kotlin.myapplication.ui.movie

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.adapter.MoviesAdapter
import com.example.kotlin.myapplication.listeners.MovieItemListener
import com.example.kotlin.myapplication.model.data.Movie
import com.example.kotlin.myapplication.model.repository.local.entity.MovieEntity
import com.example.kotlin.myapplication.ui.notes.NotesActivity
import com.example.kotlin.myapplication.utils.base.BaseAppCompactActivity
import kotlinx.android.synthetic.main.activity_movie.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseAppCompactActivity(), MovieItemListener, AnkoLogger {

    val model = MoviesViewModel()
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        adapter = MoviesAdapter(this, this)
        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.adapter = adapter
        getMoviesList()

        updateUI()

    }

    private fun updateUI() {

        model.allMovies.observe(this, Observer {
            it.let {
                adapter.setMovies(it!!)
            }
        })
    }


    private fun getMoviesList() {

        model.getMovieList("d8b709d139fa4b7d29290ffd2860f36a5994ac86").observe(this,
            Observer<ArrayList<Movie>> { movies ->
                movies.let {
                    if (movies?.isNotEmpty()!!) model.insert(movies)
                }
            })


    }


    override fun onItemClicked(movie: MovieEntity) {
        startActivity<NotesActivity>()
        toast("You have selected ${movie.title}")
    }


    override fun onInternetUnavailable() {
        super.onInternetUnavailable()
        longToast("It seems you are not connected to any active internet.")

    }


}
