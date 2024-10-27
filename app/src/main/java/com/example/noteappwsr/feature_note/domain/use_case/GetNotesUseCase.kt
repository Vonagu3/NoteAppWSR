package com.example.noteappwsr.feature_note.domain.use_case

import com.example.noteappwsr.feature_note.domain.model.Note
import com.example.noteappwsr.feature_note.domain.repository.NoteRepository
import com.example.noteappwsr.feature_note.domain.util.NoteOrder
import com.example.noteappwsr.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.reflect.KFunction1

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    sorting(noteOrder, notes::sortedBy)
//                    when(noteOrder) {
//                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
//                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
//                        is NoteOrder.Color -> notes.sortedBy { it.color }
//                    }
                }

                is OrderType.Descending -> {
                    sorting(noteOrder, notes::sortedByDescending)
//                    when(noteOrder) {
//                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
//                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
//                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
//                    }
                }
            }
        }
    }

    private fun sorting(noteOrder: NoteOrder, func: KFunction1<(Note) -> String, List<Note>>) =
        when (noteOrder) {
            is NoteOrder.Title -> func.invoke { it.title.lowercase() }
            is NoteOrder.Date -> func.invoke { it.timestamp.toString() }
            is NoteOrder.Color -> func.invoke { it.color.toString() }
        }
}