package com.example.kotlin.myapplication.ui.notes

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.adapter.NotesAdapter
import com.example.kotlin.myapplication.listeners.NoteItemsListener
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity
import com.example.kotlin.myapplication.utils.RecyclerItemTouchHelper
import com.example.kotlin.myapplication.utils.base.BaseAppCompactActivity
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class NotesActivity : BaseAppCompactActivity(), NoteItemsListener,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {


    private var model = NotesViewModel()
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        adapter = NotesAdapter(this, this)
        rvMovie.layoutManager = LinearLayoutManager(this)

        rvMovie.setSwipeListener(this)
        rvMovie.adapter = adapter
        retrieveAvailableNotes()

        add.setOnClickListener { startActivity<AddNotes>() }


    }

    private fun retrieveAvailableNotes() {

        model.allNotes.observe(this, Observer {

            it.let {
                adapter.setNotes(it!!)
            }
            if (it?.size!! <= 0) {
                model.scope.launch(Dispatchers.Main) {
                    delay(400)
                    uiNotifier.visibility = View.VISIBLE
                }
            } else {
                uiNotifier.visibility = View.GONE
            }
        })
    }

    override fun onItemClicked(note: NotesEntity) {
        toast("You have selected ${note.title}")
    }

    override fun onItemLongClicked(note: NotesEntity) {
    }

    override fun onItemDeleted(note: NotesEntity) {
        model.delete(note)  //delete from db
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        adapter.delete(position)

    }

}