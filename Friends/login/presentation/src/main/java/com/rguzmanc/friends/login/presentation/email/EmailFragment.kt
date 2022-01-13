package com.rguzmanc.friends.login.presentation.email

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.rguzmanc.friends.login.presentation.LoginActivity
import com.rguzmanc.friends.login.presentation.R
import com.rguzmanc.friends.login.presentation.password.PasswordFragment
import com.rguzmanc.friends.login.presentation.username.UsernameFragment

class EmailFragment : Fragment(){

    private lateinit var etEmail: TextInputEditText
    private lateinit var btnNext: MaterialButton
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    val usernameViewModel: UsernameViewModel by viewModels {
//        viewModelFactory
//    }

    companion object {
        const val TAG = "TESTEmailFragment"

        fun newInstance() = EmailFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this@EmailFragment)
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
        val view = inflater.inflate(R.layout.fragment_email, container, false)
        etEmail = view.findViewById(R.id.et_email)
        btnNext = view.findViewById(R.id.btn_next)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        btnNext.setOnClickListener {
            parentFragmentManager.commit {
                add(android.R.id.content, PasswordFragment.newInstance(),TAG).addToBackStack(TAG)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}