package dto

import domain.Board
import domain.Card
import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND
import domain.Position

data class BoardDto(
    val roundCoins: Int,
    val cardMap: HashMap<Position, CardDto>,
    val rowNumberCountMap: HashMap<Char, Int>,
    val columnNumberCountMap: HashMap<Int, Int>,
    val rowVoltorbCountMap: HashMap<Char, Int>,
    val columnVoltorbCountMap: HashMap<Int, Int>,
) {
    companion object {
        fun from(board: Board): BoardDto {
            val rows = (ROW_LOWER_BOUND .. ROW_UPPER_BOUND).toList()
            val columns = (COLUMN_LOWER_BOUND .. COLUMN_UPPER_BOUND).toList()

            return BoardDto(
                roundCoins = board.calculateCoins(),
                cardMap = board.getCardMap().mapValues { CardDto.from(it.value) }
                    .toMap(HashMap()),
                rowNumberCountMap = rows.associateWith { board.calculateRowNumberCount(it) }
                    .toMap(HashMap()),
                columnNumberCountMap = columns.associateWith { board.calculateColumnNumberCount(it) }
                    .toMap(HashMap()),
                rowVoltorbCountMap = rows.associateWith { board.calculateRowVoltorbCount(it)}
                    .toMap(HashMap()),
                columnVoltorbCountMap = columns.associateWith { board.calculateColumnVoltorbCount(it) }
                    .toMap(HashMap())
            )
        }
    }
}