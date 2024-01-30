package me.snowlight

import mu.KotlinLogging
import java.util.concurrent.CompletableFuture
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

fun main() {
    measureTimeMillis {
        val subA = subA()       // [비동기] 3 sec 제어권,
        subB()                  // 2 sec 제어권, 결과값
        while (!subA.isDone) {  // [동기] 제어권, 결과값 - 코드 블록으로 언제가는 두개를 같이 받는다.
            logger.debug { "...Wait" }
            Thread.sleep(500)
        }
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun subA(): CompletableFuture<Unit> {
    return CompletableFuture.supplyAsync {
        logger.debug { "Start A" }
        Thread.sleep(3000)
        logger.debug { "End A" }
    }
}

private fun subB() {
    logger.debug { "Start B" }
    Thread.sleep(2000)
    logger.debug { "End B" }
}