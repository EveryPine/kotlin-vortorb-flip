package domain

import command.Command
import command.ExitCommand
import command.FlipCommand
import command.MarkCommand
import command.StatusCommand
import command.UnmarkCommand
import io.mockk.mockk
import manager.GameManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

@DisplayName("CommandInvoker 클래스의")
class CommandInvokerTest {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("setCommand 메소드는")
    inner class SetCommandTest {

        @ParameterizedTest
        @MethodSource("provideNormalTestData")
        fun `사용자 입력을 바탕으로 실행할 커맨드와 인자 정보를 설정한다`(
            input: String, expectedCommand: Class<Command>, expectedArgs: List<String>
        ) {
            // given
            val gameManager: GameManager = mockk(relaxed = true)
            val commandInvoker = CommandInvoker(gameManager)

            // when
            commandInvoker.setCommand(input)

            // then
            Assertions.assertThat(commandInvoker).extracting("command.class", "args")
                .containsExactly(expectedCommand, expectedArgs)

        }

        @Test
        fun `잘못된 명령어가 입력된 경우 예외가 발생한다`() {
            // given
            val wrongInput = "3fdsf"
            val gameManager: GameManager = mockk(relaxed = true)
            val commandInvoker = CommandInvoker(gameManager)

            // when

            // then
            org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException::class.java) {
                commandInvoker.setCommand(wrongInput)
            }
        }

        private fun provideNormalTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("flip a", FlipCommand::class.java, listOf("a")),
                Arguments.of("mark a b", MarkCommand::class.java, listOf("a", "b")),
                Arguments.of("unmark a b", UnmarkCommand::class.java, listOf("a", "b")),
                Arguments.of("status", StatusCommand::class.java, emptyList<String>()),
                Arguments.of("exit", ExitCommand::class.java, emptyList<String>()),
            )
        }
    }

}