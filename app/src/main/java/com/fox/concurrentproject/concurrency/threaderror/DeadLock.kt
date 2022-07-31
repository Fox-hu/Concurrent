package com.fox.concurrentproject.concurrency.threaderror

/**
 * @Author Silver-Fox
 * @Date 2022/7/31 11:12
 * 死锁演示
 */
class DeadLockRunnable(val lockA: Object, val lockB: Object,var flag:Int) : Runnable {
    override fun run() {
        println("flag = $flag")
        if (flag == 1) {
            synchronized(lockA) {
                println("${Thread.currentThread().name} get lockA")
                Thread.sleep(500)
                synchronized(lockB) {
                    println("${Thread.currentThread().name} get lockB")
                }
            }
        }

        if (flag == 2) {
            synchronized(lockB) {
                println("${Thread.currentThread().name} get lockB")
                Thread.sleep(500)
                synchronized(lockA) {
                    println("${Thread.currentThread().name} get lockA")
                }
            }
        }
    }
}

fun main() {
    val lockA = Object()
    val lockB = Object()

    val r1 = DeadLockRunnable(lockA,lockB,1)
    val r2 = DeadLockRunnable(lockA,lockB,2)
    Thread(r1).start()
    Thread(r2).start()
}