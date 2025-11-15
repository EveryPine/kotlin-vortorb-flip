package dto

import domain.GameState

data class GameResultDto(
    val cumulativeCoins: Int
) {

    companion object {

        fun from(gameState: GameState): GameResultDto {
            return GameResultDto(
                cumulativeCoins = gameState.getCoins()
            )
        }
    }
}
