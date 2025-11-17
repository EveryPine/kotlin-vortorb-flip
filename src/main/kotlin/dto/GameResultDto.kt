package dto

import domain.GameState

data class GameResultDto(
    val cumulativeCoin: CoinDto
) {

    companion object {

        fun from(gameState: GameState): GameResultDto {
            return GameResultDto(
                cumulativeCoin = gameState.getCumulativeCoin()
                    .toDto()
            )
        }
    }
}
