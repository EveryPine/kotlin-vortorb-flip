package utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import kotlin.test.Test

@DisplayName("Randoms 클래스의")
class RandomsTest {

    @Nested
    @DisplayName("pickRandomNumber는")
    inner class PickRandomNumber {

        @RepeatedTest(100)
        fun `주어진 범위 사이의 무작위 정수를 반환한다`() {
            // given
            val startInclusive: Int = 3
            val endExclusive: Int = 10

            // when
            val actual = Randoms.pickRandomNumber(startInclusive, endExclusive)

            // then
            assertThat(actual)
                .isGreaterThanOrEqualTo(startInclusive)
                .isLessThanOrEqualTo(endExclusive)
        }

        @Test
        fun `끝 값이 시작 값보다 작은 경우 예외가 발생한다`() {
            // given
            val startInclusive: Int = 10
            val endExclusive: Int = 3

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                Randoms.pickRandomNumber(startInclusive, endExclusive) }
        }
    }
}