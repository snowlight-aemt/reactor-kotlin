package me.snowlight.coffee

import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

private fun main() {
    measureTimeMillis {
        repeat(2) {
            makeCoffee()
        }
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun makeCoffee() {
    grindCoffee()
    brewCoffee()
    boilMilk()
    formMilk()
    mixCoffeeAndMilk()
}

private fun grindCoffee() {
    logger.debug { "커피갈기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 가루" }
}

private fun brewCoffee() {
    logger.debug { "커피 내리기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 원액" }
}

private fun boilMilk() {
    logger.debug { "우유 데우기" }
    Thread.sleep(1000)
    logger.debug { " > 데운 우유" }
}

private fun formMilk() {
    logger.debug { "우유 거품 내기" }
    Thread.sleep(1000)
    logger.debug { " > 거품 우유" }
}

private fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 라떼" }
}