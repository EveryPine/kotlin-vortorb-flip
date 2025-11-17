package command

import domain.Board
import domain.GameState
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import manager.PrintManager
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("StatusCommand 클래스의")
class StatusCommandTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class Execute {

        @Test
        fun `PrintManager에게 게임 상태 출력을 요청한다`() {
            // given
            val gameState: GameState = mockk<GameState>(relaxed = true)
            val board: Board = mockk(relaxed = true)
            val statusCommand: Command = StatusCommand(gameState, board)
            val args: List<String> = emptyList()

            mockkObject(PrintManager)
            every { PrintManager.printGameStatus(any(), any()) } returns Unit

            // when
            statusCommand.execute(args)

            // then
            verify(exactly = 1) {
                PrintManager.printGameStatus(any(), any())
            }

        }

        @Test
        fun `명령어에 인자가 포함되어 있는 경우 예외가 발생한다`() {
            // given
            val gameState: GameState = mockk<GameState>(relaxed = true)
            val board: Board = mockk(relaxed = true)
            val statusCommand: Command = StatusCommand(gameState, board)
            val args: List<String> = listOf("wrong_arg")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                statusCommand.execute(args)
            }
        }
    }

}