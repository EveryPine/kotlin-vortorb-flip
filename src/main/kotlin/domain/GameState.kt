package domain

class GameState(
    private var coins: Int,
    private var round: Int,
    private var level: Int
) {

    fun getLevel(): Int = level

}