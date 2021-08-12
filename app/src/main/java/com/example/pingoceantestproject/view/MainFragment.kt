package com.example.pingoceantestproject.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.pingoceantestproject.R
import com.example.pingoceantestproject.databinding.FragmentMainBinding
import com.example.retrofittest.MainViewModelFactory
import repository.UserRepository
import utils.FragmentExtensions.createToast
import utils.UiUtils.createAlertDialog

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var viewModel: UsersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = UserRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(UsersViewModel::class.java)

        setHasOptionsMenu(true)
        getUserData()
    }

    private fun getUserData() {

        with(binding) {
            val args: MainFragmentArgs by navArgs()
            val token = args.token

            viewModel.getUser(token)
            viewModel.myResponse2.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    tvPhone.text = "Phone: ${response.body()?.phone}"
                    tvPosition.text = "Position: ${response.body()?.position}"
                } else {
                    createToast("Something is wrong!", false)
                }
            })
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val exitBtn = R.id.button_exit

        when(item.itemId) {
            exitBtn -> context?.let { createAlertDialog(it, R.string.do_you_want_to_exit, R.string.alert_title) }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_exit, menu)
    }
}