package me.snowlight.coffee

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}
private val single = newSingleThreadContext("worker")

private fun main() {
    measureTimeMillis {
        runBlocking {
            repeat(10) {
                launch(single) {
                    makeCoffee()
                }
            }
        }
    }.let { logger.debug {">> elasped : $it ms"} }
}

private suspend fun makeCoffee() {
    coroutineScope {
        launch {
            grindCoffee()
            brewCoffee()
        }
        launch {
            boilMilk()
            formMilk()
        }
    }
    mixCoffeeAndMilk()
}

private suspend fun grindCoffee() {
    logger.debug { "커피갈기" }
    delay(1000)
    logger.debug { " > 커피 가루" }
}

private suspend fun brewCoffee() {
    logger.debug { "커피 내리기" }
    delay(1000)
    logger.debug { " > 커피 원액" }
}

private suspend fun boilMilk() {
    logger.debug { "우유 데우기" }
    delay(1000)
    logger.debug { " > 데운 우유" }
}

private suspend fun formMilk() {
    logger.debug { "우유 거품 내기" }
    delay(1000)
    logger.debug { " > 거품 우유" }
}

private suspend fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    delay(1000)
    logger.debug { " > 커피 라떼" }
}