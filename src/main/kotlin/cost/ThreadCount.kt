package me.snowlight.cost

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val latch = CountDownLatch(10_000)
    val count = AtomicLong()
    measureTimeMillis {
        repeat(10_000) {
            thread {
                repeat(100_000) {
                    count.addAndGet(1)
                }
                latch.countDown()
            }
        }
        latch.await()
    }.let { println("count $count, elapsed: $it ms") }
}

// count 1000000000, elapsed: 52981 ms