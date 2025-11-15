package command

import domain.GameState
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("ExitCommand 클래스의")
class ExitCommandTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class Execute {

        @Test
        fun `GameState에 게임 종료를 요청한다`() {
            // given
            val gameState: GameState = mockk(relaxed = true)
            val exitCommand = ExitCommand(gameState)
            val args: List<String> = emptyList()

            // when
            exitCommand.execute(args)

            // then
            verify(exactly = 1) {
                gameState.exit()
            }
        }

        @Test
        fun `명령어에 인자가 포함되어 있는 경우 예외가 발생한다`() {
            // given
            val gameState: GameState = mockk<GameState>(relaxed = true)
            val exitCommand = ExitCommand(gameState)
            val args: List<String> = listOf("wrong_args")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) { exitCommand.execute(args) }
        }
    }

}