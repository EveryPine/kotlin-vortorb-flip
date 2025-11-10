package domain

class Card(
    private val type: CardType,
    private var state: CardState = CardState.UNFLIPPED
) {

    fun flip() {
        state = CardState.FLIPPED
    }

    fun addTo(count: Int): Int {
        return count + type.factor
    }

    fun multiplyTo(coins: Int): Int {
        return coins * type.factor
    }

    fun isTypeOf(type: CardType): Boolean {
        return this.type == type
    }

    fun isFlipped(): Boolean {
        return state == CardState.FLIPPED
    }


}