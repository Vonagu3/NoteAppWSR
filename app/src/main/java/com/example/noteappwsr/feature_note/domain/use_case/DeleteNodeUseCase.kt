package com.example.noteappwsr.feature_note.domain.use_case

import com.example.noteappwsr.feature_note.domain.model.Note
import com.example.noteappwsr.feature_note.domain.repository.NoteRepository

class DeleteNodeUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}