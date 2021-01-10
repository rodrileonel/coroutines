package com.everis.corrutinas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {

    private lateinit var vm:ThirdViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        vm = ViewModelProvider(this).get(ThirdViewModel::class.java)

        vm.loginResult.observe(this, Observer { ok->
            toast(if (ok) "Success" else "Failure")
        })

        btOk.setOnClickListener {
            vm.onLogin(etUser.text.toString(),etPw.text.toString())
        }
    }
}