package com.noteapp.roomdatabase.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noteapp.roomdatabase.model.Note
import com.noteapp.roomdatabase.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteActivityViewModel(private val repositoryObject: NoteRepository) : ViewModel() {

    fun saveNote(newNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.addNote(newNote)
    }

    private var imagePath: String? = null

    fun saveImagePath(path: String?) {
        imagePath = path
    }

    fun setImagePath(): String? {
        if (imagePath != null)
            return imagePath
        return null
    }

    fun updateNote(existingNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.updateNote(existingNote)
    }

    fun deleteNote(existingNote: Note) = viewModelScope.launch(Dispatchers.IO) {
        repositoryObject.deleteNote(existingNote)
    }

    fun searchNote(query: String): LiveData<List<Note>> {
        return repositoryObject.searchNote(query)
    }

    fun getAllNotes(): LiveData<List<Note>> = repositoryObject.getNote()

    override fun onCleared() {
        imagePath = null
        super.onCleared()
    }
}