package domain

import domain.Constants.GRID_CELL_COUNT

class CardConfig private constructor(
    private val twoCount: Int,
    private val threeCount: Int,
    private val voltorbCount: Int
) {

    private val oneCount: Int =
        GRID_CELL_COUNT - (twoCount + threeCount + voltorbCount)

    companion object {
        fun of(counts: List<Int>): CardConfig {
            validate(counts)
            return CardConfig(
                twoCount = counts[0],
                threeCount = counts[1],
                voltorbCount = counts[2]
            )
        }

        private fun validate(list: List<Int>) {
            val requiredParameterSize: Int = 3

            if (list.size != requiredParameterSize) {
                throw IllegalArgumentException("CardConfig는 ${requiredParameterSize}개의 인자가 필요합니다.")
            }
            if (list.sum() >= GRID_CELL_COUNT) {
                throw IllegalArgumentException("CardConfig 인자의 합은 ${GRID_CELL_COUNT}을 넘을 수 없습니다.")
            }
        }
    }

    fun toList(random: Boolean = true): List<Card> {
        val cards: MutableList<Card> = mutableListOf()

        cards.addAll(Array(oneCount) { Card(CardType.ONE) })
        cards.addAll(Array(twoCount) { Card(CardType.TWO) })
        cards.addAll(Array(threeCount) { Card(CardType.THREE) })
        cards.addAll(Array(voltorbCount) { Card(CardType.VOLTORB) })

        if (random) {
            cards.shuffle()
        }

        return cards
    }

}