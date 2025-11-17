package command

import domain.Board
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("MarkCommand 클래스의")
class MarkCommandTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class Execute {

        @Test
        fun `수신자에게 카드 마킹을 요청한다`() {
            // given
            val board: Board = mockk<Board>(relaxed = true)
            val markCommand = MarkCommand(board)
            val args = listOf("A1", "1")

            // when
            markCommand.execute(args)

            // then
            verify(exactly = 1) {
                board.mark(any(), any())
            }
        }

        @Test
        fun `명령 인자가 2개가 아닌 경우 예외가 발생한다`() {
            // given
            val board: Board = mockk<Board>(relaxed = true)
            val markCommand = MarkCommand(board)
            val args = listOf("A1", "1", "2")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                markCommand.execute(args)
            }
        }
    }

}