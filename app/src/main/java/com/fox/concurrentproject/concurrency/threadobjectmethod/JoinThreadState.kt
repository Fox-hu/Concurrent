package com.fox.concurrentproject.concurrency.threadobjectmethod

/**
 * @Author Silver-Fox
 * @Date 2022/7/30 21:00
 * 先join再mainThread.getState()
 * 通过debugger看线程join前后状态的对比
 */
fun main() {
    val mainThread = Thread.currentThread()
    val thread = Thread{
        Thread.sleep(3000)
        println("mainThread state = ${mainThread.state}")
        println("子线程运行结束")
    }
    thread.start()
    println("等待子线程运行结束")
    thread.join()
    println("主线程运行完毕")
}

/**
 * 等待子线程运行结束
 * mainThread state = WAITING
 * 子线程运行结束
 * 主线程运行完毕
 */