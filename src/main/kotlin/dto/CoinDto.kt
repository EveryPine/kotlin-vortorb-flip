package dto

data class CoinDto(
    val value: Int
) {

    companion object {

        fun of(value: Int): CoinDto {
            return CoinDto(value)
        }
    }
}
