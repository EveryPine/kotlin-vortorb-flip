package command

import domain.Board
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("FlipCommand 클래스의")
class FlipCommandTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class Execute {

        @Test
        fun `수신자에게 카드 뒤집기를 요청한다`() {
            // given
            val board: Board = mockk(relaxed = true)
            val flipCommand: Command = FlipCommand(board)
            val args: List<String> = listOf("A1")

            // when
            flipCommand.execute(args)

            // then
            verify(exactly = 1) {
                board.flipOf(any())
            }
        }

        @Test
        fun `명령 인자 개수가 2개 이상인 경우 예외가 발생한다`() {
            // given
            val board: Board = mockk(relaxed = true)
            val flipCommand: Command = FlipCommand(board)
            val args: List<String> = listOf("A1", "B2")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                flipCommand.execute(args)
            }
        }
    }
}