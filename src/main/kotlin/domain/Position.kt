package domain

import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND

class Position private constructor(private val row: Char, private val column: Int) {

    companion object {

        private val cache: HashMap<Pair<Char, Int>, Position> = HashMap()

        fun of(row: Char, column: Int): Position {
            validate(row, column)

            return cache.getOrPut(row to column, { Position(row, column) })
        }

        fun of(position: String): Position {
            val row: Char = position[0]
            val column: Int = position[1] - '0'
            validate(row, column)

            return cache.getOrPut(row to column, { Position(row, column) })
        }

        private fun validate(row: Char, column: Int) {
            if (row !in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
                throw IllegalArgumentException("행 위치가 범위를 벗어났습니다: $row")
            }

            if (column !in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
                throw IllegalArgumentException("열 위치가 범위를 벗어났습니다: $column")
            }
        }
    }

    fun toIndex(): Int {
        return (row - ROW_LOWER_BOUND) * (COLUMN_UPPER_BOUND - COLUMN_LOWER_BOUND + 1) +
                (column - COLUMN_LOWER_BOUND)
    }
}