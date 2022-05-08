package com.example.notesboats.usecases

import com.example.common.abstractUsecases.UseCase
import com.example.notesboats.db.Notes
import com.example.notesboats.repositories.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher

class NotesDeleteUseCase (
    val repository: NotesRepository,
    dispatcher: CoroutineDispatcher
): UseCase<Notes, Boolean>(dispatcher){

    override suspend fun execute(parameters: Notes): Boolean {

        try{
            repository.deleteNotes(parameters)
        }catch (e:Exception){
            throw e
        }

        return true

    }

}