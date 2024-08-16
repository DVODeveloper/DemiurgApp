package com.example.demiurgapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.demiurgapp.domain.entities.Cell

interface DomainRepository {


    fun addLivingCell(cell: Cell)
    fun getListOfLife(): LiveData<List<Cell>>
}