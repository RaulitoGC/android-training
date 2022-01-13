package com.rguzmanc.friends.login.presentation.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.rguzmanc.friends.home.presentation.HomeActivity
import com.rguzmanc.friends.login.presentation.LoginActivity
import com.rguzmanc.friends.login.presentation.R
import com.rguzmanc.friends.login.presentation.email.EmailFragment
import com.rguzmanc.friends.login.presentation.username.UsernameFragment

class PasswordFragment: Fragment() {

    private lateinit var etPassword: TextInputEditText
    private lateinit var btnFinish: MaterialButton
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    val usernameViewModel: UsernameViewModel by viewModels {
//        viewModelFactory
//    }

    companion object {
        const val TAG = "TESTPasswordFragment"

        fun newInstance() = PasswordFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LoginActivity).loginComponent.inject(this@PasswordFragment)
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
        val view = inflater.inflate(R.layout.fragment_password, container, false)
        etPassword = view.findViewById(R.id.et_password)
        btnFinish = view.findViewById(R.id.btn_finish)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        btnFinish.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
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