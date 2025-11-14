package dto

import domain.Board
import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND
import domain.LineHint
import domain.Position

data class BoardDto(
    val roundCoins: Int,
    val cardMap: HashMap<Position, CardDto>,
    val rowLineHintMap: HashMap<Char, LineHint>,
    val columnLineHintMap: HashMap<Int, LineHint>,
) {
    companion object {
        fun from(board: Board): BoardDto {
            val rows = (ROW_LOWER_BOUND..ROW_UPPER_BOUND).toList()
            val columns = (COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND).toList()

            return BoardDto(
                roundCoins = board.calculateCoins(),
                cardMap = board.getCardMap().mapValues { CardDto.from(it.value) }
                    .toMap(HashMap()),
                rowLineHintMap = rows.associateWith { board.calculateRowLineHint(it)}
                    .toMap(HashMap()),
                columnLineHintMap = columns.associateWith { board.calculateColumnLineHint(it) }
                    .toMap(HashMap())
            )
        }
    }
}