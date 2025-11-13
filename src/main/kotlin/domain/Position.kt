package domain

import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND

class Position private constructor(private val row: Char, private val column: Int) {

    companion object {

        private val cache: HashMap<Pair<Char, Int>, Position> = HashMap()

        fun of(row: Char, column: Int): Position {
            val uppercaseRow = row.uppercaseChar()

            validateRow(uppercaseRow)
            validateColumn(column)

            return cache.getOrPut(uppercaseRow to column, { Position(uppercaseRow, column) })
        }

        fun of(position: String): Position {
            validatePosition(position)
            val row: Char = position[0]
            val column: Int = position[1] - '0'

            return of(row, column)
        }

        private fun validatePosition(position: String) {
            if (position.length != 2) {
                throw IllegalArgumentException("올바르지 않은 위치 문자열입니다: $position")
            }
        }

        private fun validateRow(row: Char) {
            if (row !in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
                throw IllegalArgumentException("행 위치가 범위를 벗어났습니다: $row")
            }
        }

        private fun validateColumn(column: Int) {
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