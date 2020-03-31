package app.spidy.kotlinutils

import android.app.AlertDialog
import android.content.Context
import android.view.View

class Alert(private val context: Context) {
    private var builder = AlertDialog.Builder(context)
    private lateinit var alertDialog: AlertDialog
    private var onCloseCallback: (() -> Unit)? = null

    var title: String = ""
        set(value) {
            builder.setTitle(value)
        }
    var message: String = ""
        set(value) {
            builder.setMessage(value)
        }
    var icon: Int = 0
        set(value) {
            builder.setIcon(value)
        }
    var customView: View? = null
        set(value) {
            if (value != null) {
                builder.setView(value)
            }
        }
    var isCancelable = false
        set(value) {
            builder.setCancelable(value)
        }

    fun show() {
        if (!::alertDialog.isInitialized) {
            alertDialog = builder.create()
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") {_, _ ->
                onCloseCallback?.invoke()
            }
        }
        alertDialog.show()
    }

    fun dismiss() {
        alertDialog.dismiss()
    }

    fun onDismiss(callback: () -> Unit) {
        onCloseCallback = callback
    }
}