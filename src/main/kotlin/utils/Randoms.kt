package utils

object Randoms {

    fun pickRandomNumber(startInclusive: Int, endExclusive: Int) : Int {
        validateRange(startInclusive, endExclusive)

        val numbers: MutableList<Int> =
            (startInclusive..endExclusive).map { it }
            .toMutableList()
        numbers.shuffle()

        return numbers[0]
    }

    private fun validateRange(startInclusive: Int, endExclusive: Int) {
        if (endExclusive < startInclusive) {
            throw IllegalArgumentException("끝값이 시작값보다 작습니다.")
        }
    }
}