package dto

import domain.Board
import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND
import domain.GameState
import domain.Position
import kotlin.collections.toList
import kotlin.collections.toMap

data class GameStatusDto(
    val round: Int,
    val level: Int,
    val cumulativeCoins: Int,
    val currentCoints: Int,
    val cardMap: HashMap<Position, CardDto>,
    val rowLineHintMap: HashMap<Char, LineHintDto>,
    val columnLineHintMap: HashMap<Int, LineHintDto>
) {

    companion object {

        fun of(gameState: GameState, board: Board): GameStatusDto {
            val rows = (ROW_LOWER_BOUND..ROW_UPPER_BOUND).toList()
            val columns = (COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND).toList()

            return GameStatusDto(
                round = gameState.getRound(),
                level = gameState.getLevel(),
                cumulativeCoins = gameState.getCoins(),
                currentCoints = board.calculateCoins(),
                cardMap = board.getCardMap()
                    .mapValues { CardDto.from(it.value) }
                    .toMap(HashMap()),
                rowLineHintMap = rows.associateWith {
                    LineHintDto.from(
                        board.calculateRowLineHint(it)) }
                    .toMap(HashMap()),
                columnLineHintMap = columns.associateWith {
                    LineHintDto.from(
                        board.calculateColumnLineHint(it)) }
                    .toMap(HashMap())
            )
        }
    }
}
