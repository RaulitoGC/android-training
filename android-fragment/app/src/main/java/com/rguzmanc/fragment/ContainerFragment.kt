package com.rguzmanc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContainerFragment: Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ContainerFragment", "onAttach method called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ContainerFragment", "onCreate method called")
        arguments?.getString("TEST")?.let {
            Log.d("ContainerFragment", "Value from bundle = $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ContainerFragment", "onCreateView method called")
        return inflater.inflate(R.layout.container_fragment_restored, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ContainerFragment", "onViewCreated method called")
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("TEST")?.let {
            Log.d("ContainerFragment", "Value from arguments = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "ContainerFragment - $it"
        }

        savedInstanceState?.getString("TEST")?.let {
            Log.d("ContainerFragment", "Value from savedInstanceState = $it")
            view.findViewById<TextView>(R.id.txt_restore).text = "ContainerFragment - $it"
        }

        parentFragmentManager.beginTransaction().replace(R.id.fl_container, FirstRestoredFragment()).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("ContainerFragment", "onSaveInstanceState method called")
        outState.putString("TEST", "value Restored")
        super.onSaveInstanceState(outState)
    }
}