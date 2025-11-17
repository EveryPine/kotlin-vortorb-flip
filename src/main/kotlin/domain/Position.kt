package domain

data class Position(private val row: Row, private val column: Column) {

    companion object {

        private val cache: HashMap<Pair<Row, Column>, Position> = HashMap()

        val all: List<Position> = buildList() {
            for (row: Row in Row.all) {
                for (column: Column in Column.all) {
                    add(of(row, column))
                }
            }
        }

        fun of(row: Row, column: Column): Position {
            return cache.getOrPut(row to column, { Position(row, column) })
        }

        fun of(position: String): Position {
            validatePosition(position)
            val row: Row = Row.of(position[0])
            val column: Column = Column.of(position[1] - '0')

            return of(row, column)
        }

        private fun validatePosition(position: String) {
            if (position.length != 2) {
                throw IllegalArgumentException("올바르지 않은 위치 문자열입니다: $position")
            }
        }
    }

    fun toIndex(): Int {
        return row.toIndex() * Column.TOTAL_COUNT + column.toIndex()
    }

    override fun toString(): String {
        return row.toString() + column.toString()
    }

}