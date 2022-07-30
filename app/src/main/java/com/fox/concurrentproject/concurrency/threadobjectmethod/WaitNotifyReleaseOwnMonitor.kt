package com.fox.concurrentproject.concurrency.threadobjectmethod

/**
 * @Author Silver-Fox
 * @Date 2022/7/30 11:33
 * wait只会释放当前那把锁
 */

fun main() {
    val lockA = Object()
    val lockB = Object()

    val threadA = Thread{
        synchronized(lockA){
            println("ThreadA get lock a")
            synchronized(lockB){
                println("ThreadA get lock b")
                println("ThreadA release lock a")
                lockA.wait()
            }
        }
    }

    val threadB = Thread{
        Thread.sleep(1000)
        synchronized(lockA){
            println("ThreadB get lock a")
            println("ThreadB try to get lock b")
            synchronized(lockB){
                println("ThreadB get lock b")
            }
        }
    }
    threadA.start()
    threadB.start()

    /**
     * ThreadA get lock a
     * ThreadA get lock b
     * ThreadA release lock a
     * ThreadB get lock a
     * ThreadB try to get lock b
     */
}