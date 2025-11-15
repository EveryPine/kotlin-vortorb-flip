package dto

data class LevelDto(
    val value: Int
) {

    companion object {

        fun of(value: Int): LevelDto {
            return LevelDto(value)
        }
    }
}