package com.micoder.retrofit_kotlin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.micoder.retrofit_kotlin.api.ApiInterface
import com.micoder.retrofit_kotlin.api.ApiUtilities
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersApi = ApiUtilities.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch {
            try {
                val result = usersApi.getUsers()
                if (result.body() != null) {

                    // To display body of the result
                    Log.d("TAGGY", "onCreate: ${result.body()}")

                    result.body()!!.iterator().forEach {
                        Log.d("TAGGY", "name: ${it.login}")
                    }

                }
            } catch (ce: IllegalStateException) {
                throw ce // Needed for coroutine scope cancellation
            } catch (e: Exception) {
                // display error
                Log.d("TAGGY", "onCreate: Error"+e.message)
            }
        }

    }
}