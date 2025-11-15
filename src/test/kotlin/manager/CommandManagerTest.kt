package manager

import command.Command
import domain.Board
import domain.GameState
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

@DisplayName("CommandManager 클래스의")
class CommandManagerTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class InitCommandMap {

        @ParameterizedTest
        @ValueSource(strings = [
            "flip A1", "mark A1 X", "unmark", "status", "exit"])
        fun `입력한 명령 세트를 실행한다`(input: String) {
            // given
            val gameState: GameState = mockk(relaxed = true)
            val board: Board = mockk(relaxed = true)
            val command: Command = mockk(relaxed = true)
            every { command.execute(any()) } returns Unit

            val spyCommandManager: CommandManager = spyk(CommandManager, recordPrivateCalls = true)
            every { spyCommandManager["selectCommand"](any<String>()) } returns command
            spyCommandManager.initCommandMap(gameState, board)

            // when
            spyCommandManager.execute(input)

            // then
            verify(exactly = 1) { command.execute(any()) }
        }

        @Test
        fun `존재하지 않은 명령이 들어온 경우 예외가 발생한다`() {
            // given
            val input = "wrong_command a b"
            val gameState: GameState = mockk(relaxed = true)
            val board: Board = mockk(relaxed = true)

            // when
            CommandManager.initCommandMap(gameState, board)

            // them
            assertThrows(IllegalArgumentException::class.java) {
                CommandManager.execute(input)
            }
        }


    }


}