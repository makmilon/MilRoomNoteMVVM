package com.noteapp.roomdatabase.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.noteapp.roomdatabase.databinding.ActivityNoteBinding
import com.noteapp.roomdatabase.db.NoteDatabase
import com.noteapp.roomdatabase.repository.NoteRepository
import com.noteapp.roomdatabase.utils.shortToast
import com.noteapp.roomdatabase.viewmodel.NoteActivityViewModel
import com.noteapp.roomdatabase.viewmodel.NoteActivityViewModelFactory

class NoteActivity : AppCompatActivity() {

    lateinit var noteActivityViewModel: NoteActivityViewModel
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        try {
            setContentView(binding.root)
            val noteRepository = NoteRepository(NoteDatabase(this))
            val noteViewModelProviderFactory = NoteActivityViewModelFactory(noteRepository)
            noteActivityViewModel = ViewModelProvider(
                this,
                noteViewModelProviderFactory
            )[NoteActivityViewModel::class.java]
        } catch (e: Exception) {
            shortToast("error occurred")
        }
    }

}