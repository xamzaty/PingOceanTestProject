package com.example.pingoceantestproject.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PostUser
import data.Token
import data.Users
import kotlinx.coroutines.launch
import repository.UserRepository
import retrofit2.Response

class UsersViewModel (
    private val repository: UserRepository
): ViewModel() {

    var myResponse: MutableLiveData<Response<Token>> = MutableLiveData()
    var myResponse2: MutableLiveData<Response<Users>> = MutableLiveData()

    fun pushPost(postUser: PostUser) {
        viewModelScope.launch {
            val response = repository.pushPost(postUser)
            myResponse.value = response
        }
    }

    fun getUser(token: String) {
        viewModelScope.launch {
            val response = repository.getUser(token)
            myResponse2.value = response
        }
    }
}