package com.example.notesboats.usecases

import com.example.common.abstractUsecases.FlowUseCase
import com.example.common.abstractUsecases.UseCase
import com.example.common.result.Result
import com.example.notesboats.db.Notes
import com.example.notesboats.repositories.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotesUseCase(dispatcher: CoroutineDispatcher, val notesRepository: NotesRepository): FlowUseCase<Unit,List<Notes>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<Notes>>> = notesRepository.getAllNotes().map {
        Result.Success(it)
    }

}