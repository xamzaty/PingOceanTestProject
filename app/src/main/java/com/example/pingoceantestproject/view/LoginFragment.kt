package com.example.pingoceantestproject.view

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import base.BaseFragment
import com.example.pingoceantestproject.BuildConfig
import com.example.pingoceantestproject.R
import com.example.pingoceantestproject.databinding.FragmentLoginBinding
import com.example.retrofittest.MainViewModelFactory
import data.PostUser
import data.Users
import repository.UserRepository
import utils.FragmentExtensions.createSnackbar
import utils.SafeClickListener
import utils.UiUtils.toTheNextFragment
import utils.VibrationUtil.vibrate

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private lateinit var viewModel: UsersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = UserRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(UsersViewModel::class.java)

        with(binding){
            buttonEnter.setOnClickListener {
                if (!isClickRecently()) {
                    val emailText = editTextEmail.text.toString()
                    val passwordText = editTextPassword.text.toString()
                    val myPost = PostUser(emailText, passwordText)

                    viewModel.pushPost(myPost)

                    enterMainFragment()
                }

            }
        }


        if (BuildConfig.DEBUG) {
            binding.apply {
                editTextEmail.setText("testalina@mailsecv.com")
                editTextPassword.setText("1q2w3e4r5t")
            }
        }
    }

    var mLastClickTime = 0L
    fun isClickRecently(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return true
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return false
    }

    private fun enterMainFragment() {

        binding.apply {
            if(editTextEmail.text.isNullOrBlank() || editTextPassword.text.isNullOrBlank()) {
                createSnackbar("Email or Password is Empty!")
                vibrate(500)
            } else {
                postData()
            }
        }
    }

    private fun postData() {

        binding.apply {

            val repository = UserRepository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(UsersViewModel::class.java)

            viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Log.d("Main", response.body().toString())
                    val token = response.body()?.token.toString()
                    toTheNewFragment(token)
                }
                else {
                    createSnackbar("Something is wrong!")
                    vibrate(500)
                }
            })
        }
    }
    private fun toTheNewFragment(token: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment("Bearer $token")
        findNavController().navigate(action)
    }
}