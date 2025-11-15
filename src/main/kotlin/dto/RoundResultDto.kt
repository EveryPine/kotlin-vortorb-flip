package dto

import domain.Board
import domain.GameState

data class RoundResultDto(
    val round: Int,
    val level: LevelDto,
    val obtainedCoins: Int,
    val voltorbFound: Boolean
) {
    companion object {

        fun from(gameState: GameState, board: Board): RoundResultDto {
            return RoundResultDto(
                round = gameState.getRound(),
                level = gameState.getLevel().toDto(),
                obtainedCoins = board.calculateCoins(),
                voltorbFound = board.isVoltorbFound()
            )
        }
    }
}