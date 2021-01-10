package com.everis.corrutinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btOk.setOnClickListener {
            lifecycleScope.launch {
                //dos hilos en paralelo
                val ok1 = async(Dispatchers.IO) {
                    validateLogin1(etUser.text.toString(),etPw.text.toString())
                }
                val ok2 = async(Dispatchers.IO) {
                    validateLogin2(etUser.text.toString(),etPw.text.toString())
                }
                toast(if (ok1.await() && ok2.await()) "Success" else "Failure")
            }
        }
    }

    private fun validateLogin1(user: String, pw: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pw.isNotEmpty()
    }

    private fun validateLogin2(user: String, pw: String): Boolean {
        Thread.sleep(3000)
        return user.isNotEmpty() && pw.isNotEmpty()
    }
}