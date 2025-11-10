package controller

import manager.GameManager

class GameController(
    private val gameManager: GameManager
) {

    fun run() {
        while (!gameManager.isGameOver()) {
            while (!gameManager.isRoundOver()) {

            }

            gameManager.rerollRound()
        }
    }
}