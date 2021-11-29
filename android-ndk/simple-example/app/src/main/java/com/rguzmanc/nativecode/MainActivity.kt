package com.rguzmanc.nativecode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rguzmanc.nativecode.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        val sb = StringBuilder()
        sb.append(stringFromJNI())
        sb.append(getAnotherStringFromJNI())
        binding.sampleText.text = sb.toString()
        printDebugger(MainActivity.javaClass.name, "Debugger")
    }

    /**
     * A native method that is implemented by the 'nativecode' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun getAnotherStringFromJNI() : String

    companion object {
        // Used to load the 'nativecode' library on application startup.
        init {
            System.loadLibrary("nativecode")
        }

        external fun printDebugger(tag: String, message: String)
    }
}