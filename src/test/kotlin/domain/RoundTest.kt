package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

@DisplayName("Round 클래스의")
class RoundTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `라운드 객체를 생성한다`() {
            // given
            val expected: Int = 4

            // when
            val actual: Round = Round.of(expected)

            // then
            assertThat(actual).extracting("number")
                .isEqualTo(expected)
        }

        @Test
        fun `번호가 1 미만인 경우 예외가 발생한다`() {
            // given
            val number: Int = -3

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Round.of(number)
            }
        }

        @Test
        fun `번호가 10을 초과하는 경우 예외가 발생한다`() {
            // given
            val number: Int = 11

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Round.of(number)
            }
        }
    }

    @Nested
    @DisplayName("next 메소드는")
    inner class Next {

        @Test
        fun `라운드를 진행시킨다`() {
            // given
            val round: Round = Round.of(1)
            val expected: Int = 2

            // when
            round.next()

            // then
            assertThat(round).extracting("number")
                .isEqualTo(expected)
        }

        @Test
        fun `최종 라운드인 경우 플래그를 true로 변경한다`() {
            // given
            val round: Round = Round.of(10)
            val expected: Boolean = true

            // when
            round.next()

            // then
            assertThat(round).extracting("allOver")
                .isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("isFinal 메소드는")
    inner class IsFinal {

        @Test
        fun `최종 라운드인 경우 true를 반환한다`() {
            // given
            val round: Round = Round.of(10)
            val expected: Boolean = true

            // when
            val actual = round.isFinal()

            // then
            assertEquals(expected, actual)
        }

        @ParameterizedTest
        @ValueSource(ints = [1,2,3,4,5,6,7,8,9])
        fun `최종 라운드가 아닌 경우 false를 반환한다`(number: Int) {
            // given
            val round: Round = Round.of(number)
            val expected: Boolean = false

            // when
            val actual = round.isFinal()

            // then
            assertEquals(expected, actual)
        }
    }

    @Nested
    @DisplayName("isOver 메소드는")
    inner class IsOver {

        @Test
        fun `최종 라운드가 종료된 경우 true를 반환한다`() {
            // given
            val round: Round = Round.of(10)
            round.next()

            val expected: Boolean = true

            // when
            val actual = round.isOver()

            // then
            assertEquals(expected, actual)
        }

        @Test
        fun `최종 라운드가 종료되지 않았을 경우 false를 반환한다`() {
            // given
            val round: Round = Round.of(4)
            val expected: Boolean = false

            // when
            val actual = round.isOver()

            // then
            assertEquals(actual, expected)
        }
    }
}