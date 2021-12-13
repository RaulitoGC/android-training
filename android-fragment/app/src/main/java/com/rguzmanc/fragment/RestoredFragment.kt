package com.rguzmanc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class RestoredFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("RestoredFragment", "onAttach method called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RestoredFragment", "onCreate method called")
        arguments?.getString("TEST")?.let {
            Log.d("RestoredFragment", "Value from bundle = $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RestoredFragment", "onCreateView method called")
        return inflater.inflate(R.layout.fragment_restored, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("RestoredFragment", "onViewCreated method called")
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("TEST")?.let {
            Log.d("RestoredFragment", "Value from arguments = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = it
        }

        savedInstanceState?.getString("TEST")?.let {
            Log.d("RestoredFragment", "Value from savedInstanceState = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("RestoredFragment", "onSaveInstanceState method called")
        outState.putString("TEST", "value Restored")
        super.onSaveInstanceState(outState)
    }
}