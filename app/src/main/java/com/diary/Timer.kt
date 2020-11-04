package com.diary

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class Timer (lifecycle: Lifecycle): LifecycleObserver {

    var secondsCount = 0
    var allSecondsCount = 0
    private var handler = Handler()
    private var handler2 = Handler()
    private lateinit var runnable: Runnable
    private lateinit var runnable2: Runnable

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            Timber.i("Timer is at : $secondsCount")
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createTimer() {
        runnable2 = Runnable {
            allSecondsCount++
            handler2.postDelayed(runnable2, 1000)
        }
        handler2.postDelayed(runnable2, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyTimer() {
        handler2.removeCallbacks(runnable2)
        var z = secondsCount.toDouble() / allSecondsCount.toDouble() * 100
        Timber.i("X : $secondsCount , Y : $allSecondsCount , Z : $z %")
    }
}
