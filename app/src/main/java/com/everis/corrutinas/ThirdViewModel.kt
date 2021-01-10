package com.everis.corrutinas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThirdViewModel :ViewModel(){

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onLogin(user: String, pw: String) {
        viewModelScope.launch {
            _loginResult.value = withContext(Dispatchers.IO){
                validateLogin(user,pw)
            }
        }
    }

    fun validateLogin(user: String, pw: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pw.isNotEmpty()
    }
}