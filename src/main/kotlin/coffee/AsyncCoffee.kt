package me.snowlight.coffee

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}
private val single = Schedulers.newSingle("worker")

private fun main() {
    measureTimeMillis {
        Flux.range(1, 10).flatMap {
            makeCoffee()
        }.subscribeOn(single).blockLast()
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun makeCoffee(): Mono<Unit> {
    return Mono.zip(
        grindCoffee().then(brewCoffee()),
        boilMilk().then(formMilk())
    ).then(mixCoffeeAndMilk())
}

private fun grindCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피갈기" }}
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { " > 커피 가루" }}
}

private fun brewCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피 내리기" }}
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { " > 커피 원액" }}
}

private fun boilMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 데우기" }}
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { " > 데운 우유" }}
}

private fun formMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 거품 내기" }}
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { " > 거품 우유" }}
}

private fun mixCoffeeAndMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피와 우유 섞기" }}
        .delayElement(Duration.ofSeconds(1))
        .publishOn(single)
        .doOnNext { logger.debug { " > 커피 라떼" }}
}