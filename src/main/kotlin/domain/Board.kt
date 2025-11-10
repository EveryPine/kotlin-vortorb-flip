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

            if (card.isVoltorb()) {
                count++
            }
        }

        return count
    }

    fun calculateColumnVoltorbCount(column: Int): Int {
        var count: Int = 0

        for (row: Char in ROW_LOWER_BOUND..ROW_UPPER_BOUND) {
            val card: Card = cardMap[Position.of(row, column)]!!
            if (card.isVoltorb()) {
                count++
            }
        }

        return count
    }
}