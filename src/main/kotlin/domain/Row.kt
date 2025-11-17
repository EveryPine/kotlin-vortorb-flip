package domain

data class Row(
    val value: Char
) {

    companion object {

        const val ROW_LOWER_BOUND: Char = 'A'
        const val ROW_UPPER_BOUND: Char = 'E'

        private val cache: HashMap<Char, Row> = HashMap()

        val all: List<Row> = buildList() {
            for (row: Char in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
                add(of(row))
            }
        }

        fun of(value: Char): Row {
            val uppercaseValue: Char = value.uppercaseChar()
            validate(uppercaseValue)

            return cache.getOrPut(uppercaseValue, { Row(value) })
        }

        private fun validate(value: Char) {
            if (value !in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
                throw IllegalArgumentException("행 위치가 범위를 벗어났습니다: $value")
            }
        }
    }

    fun toIndex(): Int {
        return value - ROW_LOWER_BOUND
    }

    override fun toString(): String {
        return value.toString()
    }
}