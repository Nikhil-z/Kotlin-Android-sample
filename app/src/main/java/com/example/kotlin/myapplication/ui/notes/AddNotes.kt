package com.example.kotlin.myapplication.ui.notes

import android.os.Bundle
import com.example.kotlin.myapplication.R
import com.example.kotlin.myapplication.model.repository.local.entity.NotesEntity
import com.example.kotlin.myapplication.utils.base.BaseAppCompactActivity
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNotes : BaseAppCompactActivity() {

    private var model = NotesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        Fakeit.init()

        noteTitle.setErrorText("You must specify a note title.")
        noteDescription.setErrorText("Please add few words.")

        noteTitle.setTextInputLayout(noteTitleInput)
        noteDescription.setTextInputLayout(noteDescriptionInput)

        noteTitle.setText(Fakeit.artist().name())
        noteDescription.setText(Fakeit.lorem().words())

        addNote.setOnClickListener { addNewNote() }

    }


    private fun addNewNote() {

        noteTitle.isValid()
        noteDescription.isValid()

        if (noteTitle.isValid() && noteDescription.isValid()) {

            val time = System.currentTimeMillis()

            val notesEntity = NotesEntity(
                "${noteTitle.text}",
                "${noteDescription.text}",
                Fakeit.company().name(),
                time
            )

            model.insert(notesEntity)

            finish()
        }


    }


}