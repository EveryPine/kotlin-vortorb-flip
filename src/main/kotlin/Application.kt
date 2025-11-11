import controller.GameController
import domain.GameState
import manager.CommandManager
import manager.GameManager

fun main() {
    val gameState: GameState = GameState(0, 1, 1)
    val gameManager: GameManager = GameManager(gameState)
    val commandManager: CommandManager = CommandManager()
    val gameController: GameController = GameController(gameManager, commandManager)
    gameController.run()
}