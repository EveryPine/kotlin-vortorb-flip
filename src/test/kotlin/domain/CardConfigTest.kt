package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("CardConfig 클래스의")
class CardConfigTest {

    @Nested
    @DisplayName("of 메소드는")
    inner class Of {

        @Test
        fun `CardConfig 객체를 생성한다`() {
            // given
            val counts: List<Int> = listOf(3, 2, 4)

            // when
            val cardConfig: CardConfig = CardConfig.of(counts)

            // then
            assertThat(cardConfig).extracting(
                "oneCount", "twoCount", "threeCount", "voltorbCount")
                .contains(25 - (3 + 2 + 4), 3, 2, 4)

        }

        @Test
        fun `인자의 개수가 3개가 아닌 경우 예외가 발생한다`() {
            // given
            val counts: List<Int> = listOf(3, 2)

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                CardConfig.of(counts)
            }
        }

        @Test
        fun `인자의 합이 25를 초과하는 경우 예외가 발생한다`() {
            // given
            val counts: List<Int> = listOf(25, 2, 3)

            // when

            // then
            assertThrows(IllegalArgumentException::class.java) {
                CardConfig.of(counts)
            }
        }
    }

    @Nested
    @DisplayName("toList 메소드는")
    inner class ToList {

        @Test
        fun `카드 구성 정보를 리스트로 변환한다`() {
            // given
            val expected: List<Int> = listOf(16, 3, 2, 4)
            val counts: List<Int> = expected.drop(1)
            val cardConfig: CardConfig = CardConfig.of(counts)

            // when
            val cards: List<Card> = cardConfig.toList()

            // then
            for ((index: Int, type: CardType) in CardType.entries.withIndex()) {
                assertThat(cards).filteredOn("type", type)
                    .hasSize(expected[index])
            }
        }
    }
}