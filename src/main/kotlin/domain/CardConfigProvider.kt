package domain

import domain.Constants.MAX_LEVEL
import domain.Constants.MIN_LEVEL
import utils.CSVReader
import utils.Parser
import utils.Randoms

object CardConfigProvider {

    private val cardConfigListMap: HashMap<Level, MutableList<CardConfig>> = HashMap()

    init {
        initCardConfigs()
    }

    private fun initCardConfigs() {
        val absoluteFilePath: String = "src/main/resources/card_config.csv"
        val configBody: List<String> = CSVReader.readBody(absoluteFilePath)

        for (value: Int in MIN_LEVEL..MAX_LEVEL) {
            cardConfigListMap[Level.of(value)] = mutableListOf()
        }

        for (line: String in configBody) {
            val parsed: List<Int> = Parser.parseToCardConfig(line)
                .stream()
                .map { Parser.parseInt(it) }
                .toList()
            val level: Level = Level.of(parsed[0])
            val counts: List<Int> = parsed.subList(1, parsed.size)

            cardConfigListMap[level]!!.add(CardConfig.of(counts))
        }

    }

    fun provide(level: Level): CardConfig {
        val cardConfigByLevel: List<CardConfig> = cardConfigListMap[level]!!.toList()
        val randomNumber: Int = Randoms.pickRandomNumber(0, cardConfigByLevel.lastIndex)

        return cardConfigByLevel[randomNumber]
    }
}