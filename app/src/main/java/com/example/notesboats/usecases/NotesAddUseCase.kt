package com.example.notesboats.usecases

import com.example.common.abstractUsecases.UseCase
import com.example.notesboats.db.Notes
import com.example.notesboats.di.IoDispatcher
import com.example.notesboats.repositories.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesAddUseCase @Inject constructor(
    val repository: NotesRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Notes, Long>(dispatcher) {

    override suspend fun execute(parameters: Notes): Long {
        try{
            return repository.insertNotes(parameters)
        }catch (e:Exception){
            throw e
        }
    }

}