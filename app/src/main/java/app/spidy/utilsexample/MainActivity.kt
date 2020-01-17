package app.spidy.utilsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.spidy.kotlinutils.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("hello, world")
    }
}
