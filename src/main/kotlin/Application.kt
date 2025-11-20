import controller.GameSession
import domain.Board
import domain.GameState
import manager.GameStatusManager

fun main() {
    val gameState: GameState = GameState()
    val board: Board = GameStatusManager.resetBoard(gameState)
    val gameSession: GameSession = GameSession(gameState, board)

    gameSession.run()
}