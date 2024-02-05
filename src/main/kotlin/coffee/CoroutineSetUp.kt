package me.snowlight.coffee

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

suspend fun main() {
    logger.info("START")
    delay(1000)
    logger.info("END")
}