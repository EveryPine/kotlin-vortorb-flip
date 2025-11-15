package dto

import domain.LineHint

data class LineHintDto(
    val numberSum: Int,
    val voltorbCount: Int
) {

    companion object {

        fun from(lineHint: LineHint): LineHintDto {
            return LineHintDto(
                numberSum = lineHint.numberSum,
                voltorbCount = lineHint.voltorbCount
            )
        }
    }
}