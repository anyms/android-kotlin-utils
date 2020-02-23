package app.spidy.kotlinutils

import android.content.Context
import android.util.Log
import java.util.*


class TimeMachine(context: Context) {
    companion object {
        const val LAUNCH = "app.spidy.kotlinutils.LAUNCH"
        const val BACKGROUND = "app.spidy.kotlinutils.INFINITE"
        const val FOREGROUND = "app.spidy.kotlinutils.INFINITE"
    }

    private val tinyDB = TinyDB(context)

    fun schedule(tag: String, type: String = LAUNCH): Executor {
        val prevTime = tinyDB.getLong(tag, 0)
        val currentTime = Calendar.getInstance().timeInMillis
        if (prevTime == 0L) {
            tinyDB.putLong(tag, currentTime)
        }

        Log.d("hello", "prevTime: $prevTime")
        Log.d("hello", "currentTime: $currentTime")
        Log.d("hello", "diff: ${currentTime - prevTime}")

        return Executor(tag, prevTime, currentTime, type)
    }

    inner class Executor(
        private val tag: String,
        private val prevTime: Long,
        private val currentTime: Long,
        private val type: String
    ) {
        private var duration: Long = 0

        fun after(duration: Long): Executor {
            this.duration = duration
            return this
        }

        private fun onLaunch(callback: () -> Unit) {
            if (prevTime != 0L && currentTime - prevTime >= duration) {
                callback()
                tinyDB.putLong(tag, currentTime)
            }
        }

        fun run(callback: () -> Unit) {
            when (type) {
                LAUNCH -> onLaunch(callback)
            }
        }
    }
}