package me.snowlight.coffee

import mu.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

val executor = Executors.newFixedThreadPool(3)

fun main() {
    measureTimeMillis {
        repeat(10) {
            makeCoffee();
        }
        executor.shutdown()
        executor.awaitTermination(100, TimeUnit.SECONDS)
    }.let { logger.debug {">> elasped : $it ms"} }
}

private fun makeCoffee() {
    val task1 = executor.submit {
        grindCoffee()
        brewCoffee()
    }
    val task2 = executor.submit {
        boilMilk()
        formMilk()
    }

    executor.submit {
        while( ! task1.isDone || ! task2.isDone ) {
            Thread.sleep(500)
        }

        mixCoffeeAndMilk();
    }
}

private fun grindCoffee() {
    logger.debug { "커피갈기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 가루" }
}

private fun brewCoffee() {
    logger.debug { "커피 내리기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 원액" }
}

private fun boilMilk() {
    logger.debug { "우유 데우기" }
    Thread.sleep(1000)
    logger.debug { " > 데운 우유" }
}

private fun formMilk() {
    logger.debug { "우유 거품 내기" }
    Thread.sleep(1000)
    logger.debug { " > 거품 우유" }
}

private fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    Thread.sleep(1000)
    logger.debug { " > 커피 라떼" }
}