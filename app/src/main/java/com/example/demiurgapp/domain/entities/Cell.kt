package com.example.demiurgapp.domain.entities

import kotlin.random.Random

data class Cell(
    val type: String,
    val name: String,
    val description: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false

        return type == other.type && name == other.name && description == other.description
    }

    override fun hashCode(): Int {
        return 31 * type.hashCode() + 31 * name.hashCode() + 31 * description.hashCode()
    }

    companion object {
        enum class TypesOfCell {
            LIFE_CELL, DEATH_CELL
        }

        enum class LifeOrDeath {
            LIFE, DEATH
        }

        fun randomCell(): Cell {
            val types = TypesOfCell.values()
            val randomType = types[Random.nextInt(types.size)]

            return when (randomType) {
                TypesOfCell.LIFE_CELL -> Cell(
                    type = TypesOfCell.LIFE_CELL.name,
                    name = "Живая",
                    description = "и шевелится!"
                )

                TypesOfCell.DEATH_CELL -> Cell(
                    type = TypesOfCell.DEATH_CELL.name,
                    name = "Мёртвая",
                    description = "или прикидывается"
                )
            }
        }
    }
}