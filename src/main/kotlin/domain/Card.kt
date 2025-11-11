package domain

import view.OutputView

class Card(
    private val type: CardType,
    private var state: CardState = CardState.NORMAL
) {

    private var markType: CardType? = null

    fun flip() {
        if (state == CardState.FLIPPED) {
            OutputView.printInfoMessage("입력한 위치에 있는 카드는 이미 뒤집힌 상태입니다")
            return
        }
        state = CardState.FLIPPED
    }

    fun markAs(symbol: Char) {
        if (state == CardState.FLIPPED) {
            OutputView.printInfoMessage("입력한 위치에 있는 카드는 이미 뒤집힌 상태입니다")
            return
        }

        markType = CardType.of(symbol)
        state = CardState.MARKED
    }

    fun unmark() {
        if (state == CardState.FLIPPED) {
            OutputView.printInfoMessage("입력한 위치에 있는 카드는 이미 뒤집힌 상태입니다")
            return
        }
        if (state == CardState.NORMAL) {
            OutputView.printInfoMessage("입력한 위치에 있는 카드는 마킹되어있지 않은 상태입니다")
            return
        }

        markType = null
        state = CardState.NORMAL
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