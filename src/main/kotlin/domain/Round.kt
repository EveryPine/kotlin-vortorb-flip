package domain

import domain.Constants.MAX_ROUND
import domain.Constants.MIN_ROUND
import dto.RoundDto

class Round private constructor(
    private var number: Int
) {

    private var allOver: Boolean = false

    companion object {

        fun of(number: Int): Round {
            validate(number)
            return Round(number)
        }

        private fun validate(number: Int) {
            if (number < MIN_ROUND) {
                throw IllegalArgumentException("라운드 번호는 ${MIN_ROUND} 이상이어야 합니다.")
            }
            if (number > MAX_ROUND) {
                throw IllegalArgumentException("라운드 번호는 ${MAX_ROUND}를 초과할 수 없습니다.")
            }
        }
    }

    fun next() {
        if (number == MAX_ROUND) {
            allOver = true
            return
        }
        number++
    }

    fun isFinal(): Boolean {
        return number == MAX_ROUND
    }

    fun isOver(): Boolean {
        return allOver
    }

    fun toDto(): RoundDto {
        return RoundDto.of(number)
    }
}