package com.example.noteappwsr.feature_note.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNodeUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)
