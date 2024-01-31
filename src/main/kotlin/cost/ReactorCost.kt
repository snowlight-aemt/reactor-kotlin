package me.snowlight.cost

import reactor.core.publisher.Flux
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

fun main() {
    val count =  AtomicLong()
    measureTimeMillis {
        Flux.range(1, 10_000).doOnNext() {
            Flux.range(1, 100_000).doOnNext {
                count.addAndGet(1)
            }.subscribe()
        }.blockLast()
    }.let { println("count $count, elapsed: $it ms") }
}

// count 1000000000, elapsed: 7698 ms