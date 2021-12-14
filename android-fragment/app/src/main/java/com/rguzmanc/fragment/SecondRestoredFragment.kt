package com.rguzmanc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondRestoredFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("SecondRestoredFragment", "onAttach method called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondRestoredFragment", "onCreate method called")
        arguments?.getString("TEST")?.let {
            Log.d("SecondRestoredFragment", "Value from bundle =  SecondRestoredFragment - $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SecondRestoredFragment", "onCreateView method called")
        return inflater.inflate(R.layout.second_fragment_restored, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("SecondRestoredFragment", "onViewCreated method called")
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("TEST")?.let {
            Log.d("SecondRestoredFragment", "Value from arguments = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "SecondRestoredFragment - $it"
        }

        savedInstanceState?.getString("TEST")?.let {
            Log.d("SecondRestoredFragment", "Value from savedInstanceState = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "SecondRestoredFragment - $it"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("SecondRestoredFragment", "onSaveInstanceState method called")
        outState.putString("TEST", "value Restored")
        super.onSaveInstanceState(outState)
    }
}