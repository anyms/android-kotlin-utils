package app.spidy.utilsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.spidy.kotlinutils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fetch("http://httpbin.org") {
            val s = readText()
            onUiThread { textView.text = s }
        }


    }
}
