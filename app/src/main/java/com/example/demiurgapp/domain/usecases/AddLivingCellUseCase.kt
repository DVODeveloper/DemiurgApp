package com.example.demiurgapp.domain.usecases

import com.example.demiurgapp.domain.entities.Cell
import com.example.demiurgapp.domain.repository.DomainRepository

class AddLivingCellUseCase (private val domainRepository: DomainRepository) {
    fun addLivingCell(cell: Cell) {
        domainRepository.addLivingCell(cell)
    }
}