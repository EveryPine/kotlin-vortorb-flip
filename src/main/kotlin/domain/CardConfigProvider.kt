package domain

import domain.Constants.MAX_LEVEL
import domain.Constants.MIN_LEVEL
import utils.CSVReader
import utils.Parser
import utils.Randoms

object CardConfigProvider {

    private val cardConfigs: HashMap<Int, MutableList<CardConfig>> = HashMap()

    init {
        initCardConfigs()
    }

    private fun initCardConfigs() {
        val absoluteFilePath: String = "src/main/resources/card_config.csv"
        val configBody: List<String> = CSVReader.readBody(absoluteFilePath)

        for (level in MIN_LEVEL..MAX_LEVEL) {
            cardConfigs[level] = mutableListOf()
        }

        for (line in configBody) {
            val parsed = Parser.parseToCardConfig(line)
                .stream()
                .map { Parser.parseInt(it) }
                .toList()
            val level = parsed[0]
            val twoCount = parsed[1]
            val threeCount = parsed[2]
            val voltorbCount = parsed[3]

            cardConfigs[level]!!.add(CardConfig(twoCount, threeCount, voltorbCount))
        }

    }

    fun provide(level: Int): CardConfig {
        validateLevel(level)

        val cardConfigByLevel: List<CardConfig> = cardConfigs[level]!!.toList()
        val randomNumber = Randoms.pickRandomNumber(0, cardConfigByLevel.size - 1)

        return cardConfigByLevel[randomNumber]
    }

    private fun validateLevel(level: Int) {
        if (level !in MIN_LEVEL..MAX_LEVEL) {
            throw IllegalArgumentException("카드 구성 요청이 가능한 레벨의 범위를 벗어났습니다.")
        }
    }
}