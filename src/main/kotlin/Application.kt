import controller.GameController
import domain.Board
import domain.GameState
import manager.GameStatusManager

fun main() {
    val gameState: GameState = GameState()
    val board: Board = GameStatusManager.resetBoard(gameState)
    val gameController: GameController = GameController(gameState, board)

    gameController.run()
}