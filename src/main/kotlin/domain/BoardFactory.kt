package domain

object BoardFactory {

    fun create(cardConfig: CardConfig, random: Boolean): Board {
        val cards: MutableList<Card> = cardConfig.toMutableList()

        if (random) {
            cards.shuffle()
        }

        return Board(cards)
    }
}