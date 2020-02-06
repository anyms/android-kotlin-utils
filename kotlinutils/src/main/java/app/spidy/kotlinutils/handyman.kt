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

fun String.debug(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            Log.d(this@debug, "[${this.fileName}] ${this@debug}: $o")
        }
    }
}

fun String.error(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            Log.d(this@error, "[${this.fileName}] ${this@error}: $o")
        }
    }
}

fun String.warn(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            Log.d(this@warn, "[${this.fileName}] ${this@warn}: $o")
        }
    }
}

fun String.info(o: Any?) {
    if (DEBUG_MODE) {
        Throwable().stackTrace[1].apply {
            Log.d(this@info, "[${this.fileName}] ${this@info}: $o")
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
