package app.spidy.utilsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.spidy.kotlinutils.TimeMachine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeMachine = TimeMachine(this)

        timeMachine.schedule("simple_task").after(TimeMachine.HOUR).run {
            Log.d("hello","hello, world")
        }
    }
}
