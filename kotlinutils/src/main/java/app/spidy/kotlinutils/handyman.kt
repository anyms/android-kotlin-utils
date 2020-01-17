package app.spidy.kotlinutils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

fun Context.toast(o: Any?, isLong: Boolean = false) {
    val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, o.toString(), duration).show()
}

fun String.debug(o: String) {
    Log.d(this, o)
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
