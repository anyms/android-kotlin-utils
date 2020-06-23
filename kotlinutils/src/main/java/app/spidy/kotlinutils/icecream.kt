package app.spidy.kotlinutils

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception

fun Context.toast(o: Any?, isLong: Boolean = false) {
    val duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, o.toString(), duration).show()
}

fun debug(o: Any?) {
    Throwable().stackTrace[1].apply {
        Log.d("[${this.lineNumber}]:${this.fileName}", "$o")
    }
}

fun error(o: Any?) {
    Throwable().stackTrace[1].apply {
        Log.e("[${this.lineNumber}]:${this.fileName}", "$o")
    }
}

fun warn(o: Any?) {
    Throwable().stackTrace[1].apply {
        Log.w("[${this.lineNumber}]:${this.fileName}", "$o")
    }
}

fun info(o: Any?) {
    Throwable().stackTrace[1].apply {
        Log.i("[${this.lineNumber}]:${this.fileName}", "$o")
    }
}


fun onUiThread(callback: () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        callback()
    }
}

suspend fun sleep(millis: Long) {
    delay(millis)
}

fun async(block: () -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        block()
    }
}

fun ignore(callback: Exception?.() -> Unit) {
    try {
        callback(null)
    } catch (e: Exception) {
        callback(e)
    }
}


fun Context.isDarkThemeOn(): Boolean{
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}


fun Context.readFile(dir: String, fileName: String): ByteArray {
    val fileDir = getExternalFilesDir(dir)
    val file = File(fileDir, fileName)
    val fos = FileInputStream(file)
    val data = fos.readBytes()
    fos.close()
    return data
}

fun Context.readFromRawFolder(path: String): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val inputStream = resources.openRawResource(
        resources.getIdentifier(
            path,
            "raw", packageName
        )
    )

    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } != -1) {
        outputStream.write(buf, 0, len)
    }
    outputStream.close()
    inputStream.close()
    return outputStream.toByteArray()
}


fun fetch(url: String, callback: BufferedReader.() -> Unit) {
    async {
        val uri = URL(url)

        with(uri.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            callback(inputStream.bufferedReader())
        }
    }
}

fun Context.newDialog(): Dialog {
    return Dialog(this)
}
