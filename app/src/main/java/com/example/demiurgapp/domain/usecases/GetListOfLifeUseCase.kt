package com.example.demiurgapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.demiurgapp.domain.entities.Cell
import com.example.demiurgapp.domain.repository.DomainRepository

class GetListOfLifeUseCase(private val domainRepository: DomainRepository) {

    fun getListOfLife(): LiveData<List<Cell>> {
        return domainRepository.getListOfLife()
    }
}