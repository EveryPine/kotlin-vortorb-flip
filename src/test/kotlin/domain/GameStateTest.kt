package domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("GameState 클래스의")
class GameStateTest {

    @Nested
    @DisplayName("addCoins 메소드는")
    inner class AddCoins {

        @Test
        fun `현재 게임 코인에 라운드에서 획득한 코인을 추가한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState = GameState(300, 5, level)
            val roundCoins: Int = 54
            val expected: Int = 354

            // when
            gameState.addCoins(roundCoins)

            // then
            assertThat(gameState).extracting("coins")
                .isEqualTo(expected)


        }
    }

    @Nested
    @DisplayName("nextRound 메소드는")
    inner class NextRound {

        @Test
        fun `라운드를 1 증가시킨다`() {
            // given
            val level: Level = Level.of(1)
            val gameState = GameState(300, 5, level)
            val expected: Int = 6

            // when
            gameState.nextRound()

            // then
            assertThat(gameState).extracting("round")
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("advanceLevel 메소드는")
    inner class AdvanceLevel {

        @Test
        fun `레벨 업데이트를 요청한다`() {
            // given
            val level: Level = mockk(relaxed = true)
            every { level.next(any()) } returns Unit

            val gameState = GameState(300, 5, level)
            val expected: Int = 6

            // when
            gameState.advanceLevel(true)

            // then
            verify(exactly = 1) {
                level.next(any())
            }
        }
    }

    @Nested
    @DisplayName("exit 메소드는")
    inner class Exit {

        @Test
        fun `게임을 바로 종료할 수 있는 상태로 변경한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState: GameState = GameState(300, 5, level)
            val expected: GameStatus = GameStatus.EXITED

            // when
            gameState.exit()

            // then
            assertThat(gameState).extracting("status")
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("isFinalRoundOver 메소드는")
    inner class IsFinalRoundOver {

        @Test
        fun `최종 라운드가 종료되었을 경우 true를 반환한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState = GameState(300, 11, level)
            val expected = true

            // when
            val actual = gameState.isFinalRoundOver()

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `최종 라운드가 종료되지 않았을 경우 false를 반환한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState = GameState(300, 4, level)
            val expected = false

            // when
            val actual = gameState.isFinalRoundOver()

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("isExited 메소드는")
    inner class IsExited {

        @Test
        fun `게임이 종료된 경우 true를 반환한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState: GameState = GameState(300, 5, level, GameStatus.EXITED)
            val expected: Boolean = true

            // when
            val actual: Boolean = gameState.isExited()

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `게임이 종료되지 않은 경우 false를 반환한다`() {
            // given
            val level: Level = Level.of(3)
            val gameState: GameState = GameState(300, 5, level)
            val expected: Boolean = false

            // when
            val actual: Boolean = gameState.isExited()

            // then
            assertEquals(expected, actual)
        }
    }
}