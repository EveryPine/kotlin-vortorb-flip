package command

import com.sun.tools.javac.tree.TreeInfo.args
import io.mockk.mockk
import io.mockk.verify
import manager.GameManager
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
        fun `수신자에게 게임 상태 출력을 요청한다`() {
            // given
            val gameManager = mockk<GameManager>(relaxed = true)
            val statusCommand: Command = StatusCommand(gameManager)
            val args: List<String> = emptyList()

            // when
            statusCommand.execute(args)

            // then
            verify(exactly = 1) {
                gameManager.requestPrintStatus()
            }

        }

        @Test
        fun `명령어에 인자가 포함되어 있는 경우 예외가 발생한다`() {
            // given
            val gameManager = mockk<GameManager>(relaxed = true)
            val statusCommand: Command = StatusCommand(gameManager)
            val args: List<String> = listOf("wrong_arg")

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                statusCommand.execute(args)
            }
        }
    }

}