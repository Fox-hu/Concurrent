package com.fox.concurrentproject.concurrency.threadobjectmethod


fun main() {
    val waitLock = Object()

    val t1 = Thread {
        synchronized(waitLock) {
            println("线程：${Thread.currentThread().name} 开始执行")
            //释放了锁资源
            println("线程：${Thread.currentThread().name} 调用wait")
            //调用wait后 线程暂停 等待lock.notify/notifyAll后继续执行
            waitLock.wait()
            println("线程：${Thread.currentThread().name} 获取到了锁 继续执行")
        }
    }
    val t2 = Thread {
        synchronized(waitLock){
            println("线程：${Thread.currentThread().name} 开始执行")
            waitLock.notify()
            println("线程：${Thread.currentThread().name} 调用notify")
        }
    }
    t1.start()
    Thread.sleep(200)
    t2.start()
}
