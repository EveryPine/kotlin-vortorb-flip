package domain

data class Column(
    val value: Int
) {

    companion object {

        private const val COLUMN_LOWER_BOUND: Int = 1
        private const val COLUMN_UPPER_BOUND: Int = 5
        const val TOTAL_COUNT: Int = COLUMN_UPPER_BOUND - COLUMN_LOWER_BOUND + 1

        private val cache: HashMap<Int, Column> = HashMap()

        val all: List<Column> = buildList() {
            for (column: Int in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
                add(of(column))
            }
        }

        fun of(value: Int): Column {
            validate(value)

            return cache.getOrPut(value, { Column(value) })
        }

        private fun validate(value: Int) {
            if (value !in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
                throw IllegalArgumentException("열 위치가 범위를 벗어났습니다: $value")
            }
        }
    }

    fun toIndex(): Int {
        return value - COLUMN_LOWER_BOUND
    }

    override fun toString(): String {
        return value.toString()
    }
}