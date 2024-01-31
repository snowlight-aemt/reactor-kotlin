package me.snowlight

import mu.KotlinLogging
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

private val single = Schedulers.newSingle("worker")

fun main() {
    measureTimeMillis {
        Mono.zip(
            subA(),
            subB(),
        ).subscribeOn(single).block()
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun subA(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "Start A" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { "end A" } }
}

private fun subB(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "Start B" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { "end B" } }
}


