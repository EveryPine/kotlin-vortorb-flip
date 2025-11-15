import controller.GameController
import domain.Board
import domain.GameState

fun main() {
    val gameState: GameState = GameState()
    var board: Board = Board(gameState.getLevel())
    val gameController: GameController = GameController(gameState, board)

    gameController.run()
}