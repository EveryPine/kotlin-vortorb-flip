package dto

import domain.GameState

data class RoundDto(
    val round: Int
) {

    companion object {

        fun from(gameState: GameState): RoundDto {
            return RoundDto(
                round = gameState.getRound()
            )
        }
    }
}
