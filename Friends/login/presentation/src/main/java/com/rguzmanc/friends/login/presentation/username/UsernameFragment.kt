package com.rguzmanc.friends.login.presentation.username

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.rguzmanc.friends.login.presentation.LoginActivity
import com.rguzmanc.friends.login.presentation.R
import com.rguzmanc.friends.login.presentation.email.EmailFragment
import javax.inject.Inject

class UsernameFragment : Fragment() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var btnNext: MaterialButton

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val usernameViewModel: UsernameViewModel by viewModels{
        viewModelFactory
    }

    companion object {
        const val TAG = "TESTUsernameFragment"

        fun newInstance() = UsernameFragment()
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
        btnNext = view.findViewById(R.id.btn_next)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initUi()
        initViewModel()
    }

    private fun initUi(){
        btnNext.setOnClickListener {
//            val username = etUsername.text.toString()
//            usernameViewModel.login(username)
            nexScreen()
        }
    }

    private fun initViewModel(){
        usernameViewModel.login.observe(viewLifecycleOwner,{
            nexScreen()
        })

        usernameViewModel.error.observe(viewLifecycleOwner,{
            showError()
        })
    }

    private fun nexScreen(){
        parentFragmentManager.commit {
            replace(android.R.id.content, EmailFragment.newInstance(), TAG).addToBackStack(TAG)
        }
    }

    private fun showError(){
        val parentLayout = requireView()
        Snackbar.make(parentLayout, "Error!", Snackbar.LENGTH_INDEFINITE).show()
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