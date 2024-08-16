package com.example.demiurgapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demiurgapp.data.repository_impl.DomainRepositoryImpl
import com.example.demiurgapp.domain.entities.Cell
import com.example.demiurgapp.domain.usecases.AddLivingCellUseCase
import com.example.demiurgapp.domain.usecases.GetListOfLifeUseCase

class MainViewModel() : ViewModel() {

    private val repository = DomainRepositoryImpl
    private val getListOfLifeUseCase = GetListOfLifeUseCase(repository)
    private val addLivingCellUseCase = AddLivingCellUseCase(repository)


    private val _mainListLife = MutableLiveData<List<Cell>>(emptyList())
    val mainListLife: LiveData<List<Cell>> get() = _mainListLife

    init {
        observeList()
    }

    fun observeList() {
        getListOfLifeUseCase.getListOfLife().observeForever { list ->
            _mainListLife.value = list
        }
    }

    fun addItem(cell: Cell) {
        addLivingCellUseCase.addLivingCell(cell)
        checkListForMatchesVM()
    }

    private fun checkListForMatchesVM() {
        val currentList = _mainListLife.value ?: return
        val livingCount =
            currentList.takeLast(3).count { it.type == Cell.Companion.TypesOfCell.LIFE_CELL.name }
        val deadCount =
            currentList.takeLast(3).count { it.type == Cell.Companion.TypesOfCell.DEATH_CELL.name }

        if (livingCount >= 3) {
            val newLifeCell = Cell(
                type = Cell.Companion.LifeOrDeath.LIFE.name,
                name = "Жизнь",
                description = "Ку-ку!"
            )
            addLivingCellUseCase.addLivingCell(newLifeCell)
        }
        if (deadCount >= 3) {
            val newDeathCell = Cell(
                type = Cell.Companion.LifeOrDeath.DEATH.name,
                name = "Смерть",
                description = "Это полный пизнес, насяльника!!!"
            )
            addLivingCellUseCase.addLivingCell(newDeathCell)
        }
    }
}