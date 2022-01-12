package com.rguzmanc.friends.login.presentation.username

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.rguzmanc.friends.login.presentation.LoginActivity
import com.rguzmanc.friends.login.presentation.R
import javax.inject.Inject

class UsernameFragment : Fragment() {

    private lateinit var etUsername: TextInputEditText
    @Inject lateinit var viewModelFactory : ViewModelProvider.Factory

    private val usernameViewModel: UsernameViewModel by viewModels{
        viewModelFactory
    }

    companion object{
        const val TAG = "UsernameFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this@UsernameFragment)
        Log.d(TAG, "onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_username, container, false)
        etUsername = view.findViewById(R.id.et_username)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
    }
}