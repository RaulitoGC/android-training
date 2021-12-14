package com.rguzmanc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstRestoredFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FirstRestoredFragment", "onAttach method called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstRestoredFragment", "onCreate method called")
        arguments?.getString("TEST")?.let {
            Log.d("FirstRestoredFragment", "Value from bundle = $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FirstRestoredFragment", "onCreateView method called")
        return inflater.inflate(R.layout.first_fragment_restored, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("FirstRestoredFragment", "onViewCreated method called")
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("TEST")?.let {
            Log.d("FirstRestoredFragment", "Value from arguments = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "FirstRestoredFragment $it"
        }

        savedInstanceState?.getString("TEST")?.let {
            Log.d("FirstRestoredFragment", "Value from savedInstanceState = FirstRestoredFragment $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "FirstRestoredFragment $it"
        }

        parentFragmentManager.beginTransaction().replace(R.id.fl_container, SecondRestoredFragment()).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("FirstRestoredFragment", "onSaveInstanceState method called")
        outState.putString("TEST", "value Restored")
        super.onSaveInstanceState(outState)
    }
}