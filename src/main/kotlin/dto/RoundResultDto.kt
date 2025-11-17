package dto

import domain.Board
import domain.GameState

data class RoundResultDto(
    val round: RoundDto,
    val level: LevelDto,
    val obtainedCoin: CoinDto,
    val voltorbFound: Boolean,
    val finalRound: Boolean
) {
    companion object {

        fun from(gameState: GameState, board: Board): RoundResultDto {
            return RoundResultDto(
                round = gameState.getRound().toDto(),
                level = gameState.getLevel().toDto(),
                obtainedCoin = board.calculateObtainedCoin().toDto(),
                voltorbFound = board.isVoltorbFound(),
                finalRound = gameState.isFinalRound()
            )
        }
    }
}