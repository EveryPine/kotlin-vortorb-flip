package command

import com.sun.tools.javac.tree.TreeInfo.args
import io.mockk.mockk
import io.mockk.verify
import manager.GameManager
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("UnmarkCommand 클래스의")
class UnmarkCommandTest {

    @Nested
    @DisplayName("execute 메소드는")
    inner class Execute {

        @Test
        fun `수신자에게 카드 마킹 해제를 요청한다`() {
            // given
            val gameManager = mockk<GameManager>(relaxed = true)
            val unmarkCommand: Command = UnmarkCommand(gameManager)
            val args = listOf("A1")

            // when
            unmarkCommand.execute(args)

            // then
            verify(exactly = 1) {
                gameManager.requestUnmarkCard(any())
            }
        }

        @Test
        fun `명령 인자가 1개가 아닌 경우 예외가 발생한다`() {
            // given
            val gameManager = mockk<GameManager>(relaxed = true)
            val unmarkCommand: Command = UnmarkCommand(gameManager)
            val args = listOf("A1", "A2")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                unmarkCommand.execute(args)
            }
        }
    }
}