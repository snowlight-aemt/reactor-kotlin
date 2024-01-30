package me.snowlight

import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

fun main() {
    measureTimeMillis {
        subA()
        subB()
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun subA() {
    logger.debug { "Start A" }
    Thread.sleep(2000)
    logger.debug { "End A" }
}

private fun subB() {
    logger.debug { "Start B" }
    Thread.sleep(1000)
    logger.debug { "End B" }
}