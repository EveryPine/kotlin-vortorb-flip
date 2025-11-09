import controller.GameController
import domain.GameState
import manager.GameManager

fun main() {
    val gameState: GameState = GameState(0, 1, 1)
    val gameManager: GameManager = GameManager(gameState)
    val gameController: GameController = GameController(gameManager)
    gameController.run()
}