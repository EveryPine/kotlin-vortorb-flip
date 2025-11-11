package domain

import domain.Constants.COLUMN_LOWER_BOUND
import domain.Constants.COLUMN_UPPER_BOUND
import domain.Constants.ROW_LOWER_BOUND
import domain.Constants.ROW_UPPER_BOUND

class Board(private val cards: List<Card>) {

    private val cardMap: HashMap<Position, Card> = HashMap()

    init {
        mapCardsToPosition()
    }

    private fun mapCardsToPosition() {
        for (row in ROW_LOWER_BOUND .. ROW_UPPER_BOUND) {
            for (column in COLUMN_LOWER_BOUND .. COLUMN_UPPER_BOUND) {
                val position: Position = Position.of(row, column)
                cardMap[position] = cards[position.toIndex()]
            }
        }
    }

    fun flipOf(position: Position) {
        cardMap[position]!!.flip()
    }

    fun mark(position: Position, symbol: Char) {
        cardMap[position]!!.markAs(symbol)
    }

    fun calculateRowNumberCount(row: Char): Int {
        var count: Int = 0

        for (column: Int in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!
            count = card.addTo(count)
        }

        return count
    }

    fun calculateColumnNumberCount(column: Int): Int {
        var count: Int = 0

        for (row: Char in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!
            count = card.addTo(count)
        }

        return count
    }

    fun calculateRowVoltorbCount(row: Char): Int {
        var count: Int = 0

        for (column: Int in COLUMN_LOWER_BOUND..COLUMN_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!

            if (card.isTypeOf(CardType.VOLTORB)) {
                count++
            }
        }

        return count
    }

    fun calculateColumnVoltorbCount(column: Int): Int {
        var count: Int = 0

        for (row: Char in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!
            if (card.isTypeOf(CardType.VOLTORB)) {
                count++
            }
        }

        return count
    }

    fun calculateCoins(): Int {
        var coins: Int = 1

        for (card in cards) {
            if (card.isFlipped()) {
                coins = card.multiplyTo(coins)
            }
        }

        return coins
    }

    fun isAllTwoFound(): Boolean {
        for (card: Card in cardMap.values) {
            if (card.isTypeOf(CardType.TWO) && !card.isFlipped()) {
                return false
            }
        }

        return true
    }

    fun isAllThreeFound(): Boolean {
        for (card: Card in cardMap.values) {
            if (card.isTypeOf(CardType.THREE) && !card.isFlipped()) {
                return false
            }
        }

        return true
    }

    fun isVoltorbFound(): Boolean {
        for (card: Card in cardMap.values) {
            if (card.isTypeOf(CardType.VOLTORB) && card.isFlipped()) {
                return true
            }
        }

        return false
    }
}