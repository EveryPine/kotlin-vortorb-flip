package domain

import dto.CoinDto

class Coin private constructor(
    private var value: Int
) {

    companion object {
        fun of(value: Int): Coin {
            validateValue(value)
            return Coin(value)
        }

        private fun validateValue(value: Int) {
            if (value < 0) {
                throw IllegalArgumentException("코인은 0 또는 양수여야 합니다.")
            }
        }
    }

    fun add(coin: Coin) {
        this.value += coin.value
    }

    fun toDto(): CoinDto {
        return CoinDto.of(value)
    }
}