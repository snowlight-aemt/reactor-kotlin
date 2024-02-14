package me.snowlight.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ForkJoinWorkerThread
import kotlin.math.log
import kotlin.math.sin
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}
private val single = newSingleThreadContext("worker")


// # 쓰레드 처럼 동시에 실행 시키고 처리 결과를 기달리는 방법 실습

private suspend fun downloadA(): Int {
    repeat(1) {
        logger.debug { "download A" }
        delay(1.seconds)
    }
    return 1
}
private suspend fun downloadB(): Int {
    repeat(3) {
        logger.debug { "download B" }
        delay(1.seconds)
    }
    return 3
}
private suspend fun downloadC(): Int {
    repeat(5) {
        logger.debug { "download C" }
        delay(1.seconds)
    }
    return 5
}

// 태스크가 비동기 처리되는 것이지 실행 플로우가 변경되는 것이 아니다.
// A -> B -> C
//suspend fun main() {
//    coroutineScope {
//        downloadA()
//        downloadB()
//        downloadC()
//    }
//}

//
// 실행 플로오와 상관잆이 무질서하게 호출하면 내려된다.
//suspend fun main() {
//    coroutineScope {
////        coroutineScope {
////            launch { downloadA() }
////            launch { downloadB() }
////            launch { downloadC() }
////        }
//        listOf (
//            launch { downloadA() },
//            launch { downloadB() },
//            launch { downloadC() },
//        ).joinAll()
//        logger.debug { ">> END " }
//    }
//    logger.debug { ">> END FINALLY" }
//}

// 실행 플로오와 상관잆이 무질서하게 호출하면 내려된다.
 // 결과가 나올 때까지, await 에서 대기하다가 넘어간다.
suspend fun main() {
    coroutineScope {
        val taskA = async { downloadA() }
        val taskB = async { downloadB() }
        val taskC = async { downloadC() }
        val c = taskA.await() + taskB.await() + taskC.await()
        logger.debug { ">> END ${c}" }
    }
    logger.debug { ">> END FINALLY" }
}