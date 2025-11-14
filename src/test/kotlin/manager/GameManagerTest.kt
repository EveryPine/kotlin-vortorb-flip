package manager

import domain.Board
import domain.CardType
import domain.GameState
import domain.GameStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("GameManager 클래스의")
class GameManagerTest {

    @Nested
    @DisplayName("isGameOver 메소드는")
    inner class IsGameOver {

        @Test
        fun `최종 라운드가 종료된 경우 true를 반환한다`() {
            // given
            val gameState = mockk<GameState>(relaxed = true)
            val board = mockk<Board>(relaxed = true)
            val gameManager = GameManager(gameState, board)
            every { gameState.isFinalRoundOver() } returns true
            val expected = true

            // when
            val actual = gameManager.isGameOver()

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `사용자에 의해 종료된 경우 true를 반환한다`() {
            // given
            val gameState = mockk<GameState>(relaxed = true)
            val board = mockk<Board>(relaxed = true)
            val gameManager = GameManager(gameState, board)
            val expected = true

            // when
            gameManager.exitGame()
            val actual = gameManager.isGameOver()

            // then
            assertEquals(expected, actual)
        }
    }

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
            every { board.isAllFound(CardType.TWO) } returns true
            every { board.isAllFound(CardType.THREE) } returns true
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

    @Nested
    @DisplayName("exitGame 메소드는")
    inner class ExitGame {

        @Test
        fun `게임이 종료될 수 있는 상태로 변경한다`() {
            // given
            val gameState = GameState(0, 1, 1)
            val gameManager = GameManager(gameState)
            val expected = GameStatus.EXITED

            // when
            gameManager.exitGame()

            // then
            assertThat(gameManager).extracting("gameStatus")
                .isEqualTo(expected)

        }
    }

    @Nested
    @DisplayName("rerollRound 메소드는")
    inner class RerollRound {

        @Test
        fun `라운드 결과를 게임 상태에 반영하고 보드판을 재생성한다`() {
            // given
            val gameState = mockk<GameState>(relaxed = true)

            mockkObject(GameManager.Companion)
            val board = mockk<Board>(relaxed = true)
            every { GameManager.createBoard(any()) } returns board

            val gameManager = GameManager(gameState, board)

            // when
            gameManager.rerollRound()

            // then
            verify(exactly = 1) {
                gameState.addCoins(any())
                gameState.nextRound()
                gameState.nextLevel(any())
                GameManager.createBoard(any())
            }
        }
    }

}