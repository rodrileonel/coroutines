package com.everis.corrutinas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        btOk.setOnClickListener {
            //GlobalScope.launch (Dispatchers.Main) {
            launch {
                val ok = withContext(Dispatchers.IO) {
                    validateLogin(etUser.text.toString(),etPw.text.toString())
                }
                toast(if (ok) "Success" else "Failure")
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun validateLogin(user: String, pw: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pw.isNotEmpty()
    }

}