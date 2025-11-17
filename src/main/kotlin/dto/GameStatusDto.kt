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
    val round: RoundDto,
    val level: LevelDto,
    val cumulativeCoin: CoinDto,
    val currentCoin: CoinDto,
    val cardMap: HashMap<Position, CardDto>,
    val rowLineHintMap: HashMap<Char, LineHintDto>,
    val columnLineHintMap: HashMap<Int, LineHintDto>
) {

    companion object {

        fun of(gameState: GameState, board: Board): GameStatusDto {
            val rows = (ROW_LOWER_BOUND..ROW_UPPER_BOUND).toList()
            val columns = (COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND).toList()

            return GameStatusDto(
                round = gameState.getRound()
                    .toDto(),
                level = gameState.getLevel()
                    .toDto(),
                cumulativeCoin = gameState.getCumulativeCoin()
                    .toDto(),
                currentCoin = board.calculateObtainedCoin()
                    .toDto(),
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
