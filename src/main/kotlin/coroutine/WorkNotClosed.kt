package me.snowlight.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

fun main() {
    measureTimeMillis {
        runBlocking {
            launch { workHard() }
            launch { workEasy() }
        }
    }.let { logger.debug {">> elasped : $it ms"} }
}

private suspend fun workHard() {
    logger.debug { "start hard work " }
//    delay(3.seconds)
    while(true) {

    }
    logger.debug { "end hard work " }
}

private suspend fun workEasy() {
    logger.debug { "start easy work " }
    delay(1.seconds)
    logger.debug { "end easy work " }
}