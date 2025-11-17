package dto

import domain.Board
import domain.GameState

data class RoundResultDto(
    val round: Int,
    val level: LevelDto,
    val obtainedCoin: CoinDto,
    val voltorbFound: Boolean
) {
    companion object {

        fun from(gameState: GameState, board: Board): RoundResultDto {
            return RoundResultDto(
                round = gameState.getRound(),
                level = gameState.getLevel().toDto(),
                obtainedCoin = board.calculateObtainedCoin().toDto(),
                voltorbFound = board.isVoltorbFound()
            )
        }
    }
}