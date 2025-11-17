package dto

data class RoundDto(
    val number: Int
) {

    companion object {

        fun of(number: Int): RoundDto {
            return RoundDto(number)
        }
    }
}
