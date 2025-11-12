package dto

import domain.GameState

data class GameStateDto(
    val totalCoins: Int,
    val level: Int
) {
    companion object {
        fun from(gameState: GameState): GameStateDto {
            return GameStateDto(
                totalCoins = gameState.getCoins(),
                level = gameState.getLevel()
            )
        }
    }
}