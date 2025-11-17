package dto

import domain.Board
import domain.Column
import domain.GameState
import domain.Position
import domain.Row
import kotlin.collections.toMap

data class GameStatusDto(
    val round: RoundDto,
    val level: LevelDto,
    val cumulativeCoin: CoinDto,
    val currentCoin: CoinDto,
    val cardMap: HashMap<Position, CardDto>,
    val rowLineHintMap: HashMap<Row, LineHintDto>,
    val columnLineHintMap: HashMap<Column, LineHintDto>
) {

    companion object {

        fun of(gameState: GameState, board: Board): GameStatusDto {

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
                rowLineHintMap = Row.all.associateWith {
                    LineHintDto.from(
                        board.calculateRowLineHint(it)) }
                    .toMap(HashMap()),
                columnLineHintMap = Column.all.associateWith {
                    LineHintDto.from(
                        board.calculateColumnLineHint(it)) }
                    .toMap(HashMap())
            )
        }
    }
}
