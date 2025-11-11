import controller.GameController
import domain.GameState
import domain.CommandInvoker
import manager.GameManager

fun main() {
    val gameState: GameState = GameState(0, 1, 1)
    val gameManager: GameManager = GameManager(gameState)
    val commandInvoker: CommandInvoker = CommandInvoker(gameManager)
    val gameController: GameController = GameController(gameManager, commandInvoker)
    gameController.run()
}