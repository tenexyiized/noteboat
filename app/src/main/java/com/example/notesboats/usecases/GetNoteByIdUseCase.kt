package com.example.notesboats.usecases

import com.example.common.abstractUsecases.UseCase
import com.example.notesboats.db.Notes
import com.example.notesboats.di.IoDispatcher
import com.example.notesboats.repositories.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNoteByIdUseCase @Inject constructor(
    val repository: NotesRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Long, Notes?>(dispatcher) {

    override suspend fun execute(parameters: Long): Notes? {
        try{
            return repository.getNote(parameters)
        }catch (e:Exception){
            throw e
        }
    }

}