package app.spidy.utilsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import app.spidy.kotlinutils.Alert
import app.spidy.kotlinutils.TimeMachine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeMachine = TimeMachine(this)

        timeMachine.schedule("simple_task").after(TimeMachine.HOUR).run {
            Log.d("hello","hello, world")
        }
        val v = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        val alert = Alert(this).apply {
            title = "Alert!"
            customView = v
        }
        alert.show()
    }
}
