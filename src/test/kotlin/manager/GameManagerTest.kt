package manager

import domain.Board
import domain.GameState
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("GameManager 클래스의")
class GameManagerTest {

    @Nested
    @DisplayName("isRoundOver 메소드는")
    inner class IsRoundOver {

        @Test
        fun `숫자 2, 3 카드가 모두 발견된 경우 true를 반환한다`() {
            // given
            val board: Board = mockk(relaxed = true)
            val gameState = GameState(0, 1, 1)
            val gameManager = GameManager(gameState, board)
            val expected = true

            // when
            every { board.isAllTwoFound() } returns true
            every { board.isAllThreeFound() } returns true
            val actual = gameManager.isRoundOver()

            // then
            assertEquals(expected, actual)

        }

        @Test
        fun `찌리리공 카드가 발견된 경우 true를 반환한다`() {
            // given
            val board: Board = mockk(relaxed = true)
            val gameState = GameState(0, 1, 1)
            val gameManager = GameManager(gameState, board)
            val expected = true

            // when
            every { board.isVoltorbFound() } returns true
            val actual = gameManager.isRoundOver()

            // then
            assertEquals(expected, actual)
        }
    }

}