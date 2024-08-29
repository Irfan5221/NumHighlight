package com.task.numhighlight.utils

import android.app.Application
import android.util.Log

class NumberGridApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("NumberGridApplication", "Uncaught exception in thread ${thread.name}", throwable)
            // Optionally, restart the app or show a crash dialog
        }
    }
}
