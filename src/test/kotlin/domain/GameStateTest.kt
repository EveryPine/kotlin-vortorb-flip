package domain

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
            val gameState = GameState(300, 5, 3)
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
            val gameState = GameState(300, 5, 1)
            val expected: Int = 6

            // when
            gameState.nextRound()

            // then
            assertThat(gameState).extracting("round")
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("nextLevel 메소드는")
    inner class NextLevel {

        @Test
        fun `찌리리공 카드가 발견된 경우 레벨을 1만큼 내린다`() {
            // given
            val gameState = GameState(300, 5, 3)
            val voltorbFound = true
            val expected: Int = 2

            // when
            gameState.nextLevel(voltorbFound)

            // then
            assertThat(gameState).extracting("level")
                .isEqualTo(expected)

        }

        @Test
        fun `찌리리공 카드가 발견되지 않은 경우 레벨을 1만큼 올린다`() {
            // given
            val gameState = GameState(300, 5, 3)
            val voltorbFound = false
            val expected: Int = 4

            // when
            gameState.nextLevel(voltorbFound)

            // then
            assertThat(gameState).extracting("level")
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("isFinalRoundOver 메소드는")
    inner class IsFinalRound {

        @Test
        fun `최종 라운드가 종료되었을 경우 true를 반환한다`() {
            // given
            val gameState = GameState(300, 11, 3)
            val expected = true

            // when
            val actual = gameState.isFinalRoundOver()

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `최종 라운드가 종료되지 않았을 경우 false를 반환한다`() {
            // given
            val gameState = GameState(300, 4, 3)
            val expected = false

            // when
            val actual = gameState.isFinalRoundOver()

            // then
            assertEquals(expected, actual)
        }
    }
}