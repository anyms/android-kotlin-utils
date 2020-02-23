package app.spidy.kotlinutils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.lang.Exception


var DEBUG_MODE = false

fun Context.toast(o: Any?, isLong: Boolean = false) {
    val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, o.toString(), duration).show()
}

fun debug(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            var spaces = ""
            for (i in 0 until (20 - this.fileName.length)) {
                spaces += " "
            }
            Log.d(this.fileName, "$o")
        }
    }
}

fun error(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            var spaces = ""
            for (i in 0 until (20 - this.fileName.length)) {
                spaces += " "
            }
            Log.e(this.fileName, "$o")
        }
    }
}

fun warn(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            var spaces = ""
            for (i in 0 until (20 - this.fileName.length)) {
                spaces += " "
            }
            Log.w(this.fileName, "$o")
        }
    }
}

fun info(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            var spaces = ""
            for (i in 0 until (20 - this.fileName.length)) {
                spaces += " "
            }
            Log.i(this.fileName, "$o")
        }
    }
}

fun Context.onUiThread(callback: (context: Context) -> Unit) {
    Handler(this.mainLooper).post {
        callback(this)
    }
}

fun onUiThread(callback: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        callback()
    }
}

fun ignore(callback: () -> Unit) {
    try {
        callback()
    } catch (e: Exception) {}
}
