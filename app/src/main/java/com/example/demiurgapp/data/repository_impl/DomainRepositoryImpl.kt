package com.example.demiurgapp.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demiurgapp.domain.entities.Cell
import com.example.demiurgapp.domain.repository.DomainRepository

object DomainRepositoryImpl : DomainRepository {

    private val _listOfLifeLD = MutableLiveData<List<Cell>>(emptyList())
    val listOfLifeLD: LiveData<List<Cell>> get() = _listOfLifeLD

    override fun addLivingCell(cell: Cell) {
        val currentList = _listOfLifeLD.value?.toMutableList() ?: mutableListOf()
        currentList.add(cell)
        _listOfLifeLD.postValue(currentList)
    }

    override fun getListOfLife(): LiveData<List<Cell>> {
        return listOfLifeLD
    }
}