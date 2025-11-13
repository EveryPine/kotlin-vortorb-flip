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

        for (level: Int in MIN_LEVEL..MAX_LEVEL) {
            cardConfigs[level] = mutableListOf()
        }

        for (line: String in configBody) {
            val parsed: List<Int> = Parser.parseToCardConfig(line)
                .stream()
                .map { Parser.parseInt(it) }
                .toList()
            val level: Int = parsed[0]
            val counts: List<Int> = parsed.subList(1, parsed.size)

            cardConfigs[level]!!.add(CardConfig.of(counts))
        }

    }

    fun provide(level: Int): CardConfig {
        validateLevel(level)

        val cardConfigByLevel: List<CardConfig> = cardConfigs[level]!!.toList()
        val randomNumber: Int = Randoms.pickRandomNumber(0, cardConfigByLevel.lastIndex)

        return cardConfigByLevel[randomNumber]
    }

    private fun validateLevel(level: Int) {
        if (level !in MIN_LEVEL..MAX_LEVEL) {
            throw IllegalArgumentException("카드 구성 요청이 가능한 레벨의 범위를 벗어났습니다.")
        }
    }
}