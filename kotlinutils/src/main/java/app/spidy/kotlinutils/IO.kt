package app.spidy.kotlinutils

import android.content.Context
import java.io.*


class IO(private val context: Context) {
    companion object {
        const val RAW_FOLDER = "app.spidy.kotlinutils.RAW_FOLDER"
        const val INTERNAL_FOLDER = "app.spidy.kotlinutils.INTERNAL_FOLDER"
    }

    fun read(fileName: String, type: String, path: String? = null): ByteArray? {
        when(type) {
            RAW_FOLDER -> {
                val outputStream = ByteArrayOutputStream()
                val inputStream = context.resources.openRawResource(
                    context.resources.getIdentifier(
                        fileName,
                        "raw", context.packageName
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
            INTERNAL_FOLDER -> {
                val fileDir = context.getExternalFilesDir(path)
                val file = File(fileDir, fileName)
                val fos = FileInputStream(file)
                val data = fos.readBytes()
                fos.close()
                return data
            }
        }

        return null
    }
}