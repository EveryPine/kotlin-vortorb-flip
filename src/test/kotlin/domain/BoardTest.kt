package domain

import domain.Constants.GRID_EDGE_LENGTH
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Board 클래스의")
class BoardTest {

    @Nested
    @DisplayName("calculateRowNumberCount 메소드는")
    inner class CalculateRowNumberCount {

        @Test
        fun `행에 있는 모든 숫자의 합을 리턴한다`() {
            // given
            val cardOrder = provideCards()
            val board: Board = Board(cardOrder)
            val row: Char = 'A'
            val expected: Int = 7

            // when
            val actual: Int = board.calculateRowNumberCount(row)

            // then
            assertEquals(expected, actual)
        }

        private fun provideCards(): List<Card> {
            val cardOrder: MutableList<Card> = mutableListOf()

            repeat(GRID_EDGE_LENGTH * GRID_EDGE_LENGTH) {
                cardOrder.add(Card(CardType.ONE))
            }
            cardOrder[Position.of('A', 1).toIndex()] = Card(CardType.TWO)
            cardOrder[Position.of('A', 2).toIndex()] = Card(CardType.TWO)

            return cardOrder
        }
    }

    @Nested
    @DisplayName("calculateColumnNumberCount 메소드는")
    inner class CalculateColumnNumberCount {

        @Test
        fun `열에 있는 모든 숫자의 합을 리턴한다`() {
            // given
            val cardOrder = provideCards()
            val board: Board = Board(cardOrder)
            val column: Int = 1
            val expected: Int = 7

            // when
            val actual: Int = board.calculateColumnNumberCount(column)

            // then
            assertEquals(expected, actual)
        }

        private fun provideCards(): List<Card> {
            val cardOrder: MutableList<Card> = mutableListOf()

            repeat(GRID_EDGE_LENGTH * GRID_EDGE_LENGTH) {
                cardOrder.add(Card(CardType.ONE))
            }
            cardOrder[Position.of('A', 1).toIndex()] = Card(CardType.TWO)
            cardOrder[Position.of('B', 1).toIndex()] = Card(CardType.TWO)

            return cardOrder
        }

    }

    @Nested
    @DisplayName("calculateRowVoltorbCount 메소드는")
    inner class CalculateRowVoltorbCount {

        @Test
        fun `행에 있는 모든 찌리리공의 개수를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            val board: Board = Board(cards)
            val row: Char = 'A'
            val expected: Int = 2

            // when
            val actual = board.calculateRowVoltorbCount(row)

            // then
            assertEquals(expected, actual)

        }

        private fun provideCards(): List<Card> {
            val cardOrder: MutableList<Card> = mutableListOf()

            repeat(GRID_EDGE_LENGTH * GRID_EDGE_LENGTH) {
                cardOrder.add(Card(CardType.ONE))
            }
            cardOrder[Position.of('A', 1).toIndex()] = Card(CardType.VOLTORB)
            cardOrder[Position.of('A', 2).toIndex()] = Card(CardType.VOLTORB)

            return cardOrder
        }

    }

    @Nested
    @DisplayName("calculateColumnVoltorbCount 메소드는")
    inner class CalculateColumnVoltorbCount {

        @Test
        fun `열에 있는 모든 찌리리공의 개수를 반환한다`() {
            // given
            val cards: List<Card> = provideCards()
            val board: Board = Board(cards)
            val column: Int = 1
            val expected: Int = 2

            // when
            val actual = board.calculateColumnVoltorbCount(column)

            // then
            assertEquals(expected, actual)

        }

        private fun provideCards(): List<Card> {
            val cardOrder: MutableList<Card> = mutableListOf()

            repeat(GRID_EDGE_LENGTH * GRID_EDGE_LENGTH) {
                cardOrder.add(Card(CardType.ONE))
            }
            cardOrder[Position.of('A', 1).toIndex()] = Card(CardType.VOLTORB)
            cardOrder[Position.of('B', 1).toIndex()] = Card(CardType.VOLTORB)

            return cardOrder
        }

    }
}