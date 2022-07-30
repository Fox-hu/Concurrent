package com.fox.concurrentproject.concurrency.threadobjectmethod

/**
 * @Author fox
 * @Date 2022/7/30 00:29
 * 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 * 可能有废操作 一个线程可能会多次执行但是不打印 有性能浪费
 */
fun main() {
    var count = 0
    val lock = Object()

    Thread({
        while (count < 100) {
            synchronized(lock) {
                if ((count % 2) == 0) {
                    println("${Thread.currentThread().name} : ${count++}")
                }
            }
        }
    }, "偶数").start()
    Thread({
        while (count < 100) {
            synchronized(lock) {
                if ((count % 2) == 1) {
                    println("${Thread.currentThread().name} : ${count++}")
                }
            }
        }
    }, "奇数").start()
}