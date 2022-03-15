package com.androidatc.marialangdon_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        bt_login.setOnClickListener() {
            if (etEmail.text.trim().toString().isNotEmpty()
                ||etPassword.text.trim().toString().isNotEmpty()) {

                login(etEmail.text.trim().toString(),
                    etPassword.text.trim().toString()) }
            else {
                Snackbar.make(
                    findViewById(R.id.bt_login),
                    "check your username or password then try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    //if login is not empty and if it is correct, the app will continue to the next activity
    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, choose::class.java))
                }
                else {
                    Snackbar.make (
                        findViewById(R.id.bt_login),
                        "Enter a valid username or password",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
    }
}

