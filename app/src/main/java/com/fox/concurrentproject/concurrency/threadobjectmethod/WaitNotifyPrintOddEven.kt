package com.fox.concurrentproject.concurrency.threadobjectmethod

/**
 * @Author fox
 * @Date 2022/5/4 11:25
 * 两个线程交替打印0~100的奇偶数 用wait和notify来实现
 */

class OddEvenRunnable: Runnable {
    var count = 0
    private val oddEvenLock = Object()

    override fun run() {
        while (count <= 100) {
            synchronized(oddEvenLock) {
                //1、拿到锁我们就打印
                println("${Thread.currentThread().name} : ${count++}")
                //2、打印完 唤醒其他线程 自己就休眠
                oddEvenLock.notify()
                //这里还需要判断一次count <= 100 是因为之前的count++了
                if(count <= 100)
                    oddEvenLock.wait()
            }
        }
    }
}


fun main() {
    val runnable = OddEvenRunnable()
    Thread(runnable, "偶数线程").start()
    Thread(runnable, "奇数线程").start()
}