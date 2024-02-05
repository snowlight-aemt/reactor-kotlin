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
private val single = newSingleThreadContext("worker")

// WorkNotClosed 와 비교 해보자
fun main() {
    measureTimeMillis {
        runBlocking {
            var taskWorkHard = launch(single) { workHard() }
            launch(single) { workEasy() }

            delay(3.seconds)
            taskWorkHard.cancel()
            logger.debug { "End" }
        }
    }.let { logger.debug {">> elasped : $it ms"} }
}

private suspend fun workHard() {
    logger.debug { "start hard work " }
//    delay(3.seconds)
    try {
        while(true) {delay(100.milliseconds) }
    } finally {
        logger.debug { "end hard work " }
    }
}

private suspend fun workEasy() {
    logger.debug { "start easy work " }
    delay(1.seconds)
    logger.debug { "end easy work " }
}