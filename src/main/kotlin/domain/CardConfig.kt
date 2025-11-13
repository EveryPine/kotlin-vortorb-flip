package domain

class CardConfig(
    private val twoCount: Int,
    private val threeCount: Int,
    private val voltorbCount: Int
) {

    private val oneCount: Int =
        Constants.GRID_EDGE_LENGTH * Constants.GRID_EDGE_LENGTH - (twoCount + threeCount + voltorbCount)

    fun toMutableList(): MutableList<Card> {
        val cards: MutableList<Card> = mutableListOf()

        cards.addAll(Array(oneCount, { Card(CardType.ONE) }))
        cards.addAll(Array(twoCount, { Card(CardType.TWO) }))
        cards.addAll(Array(threeCount, { Card(CardType.THREE) }))
        cards.addAll(Array(voltorbCount, { Card(CardType.VOLTORB) }))

        return cards
    }

}