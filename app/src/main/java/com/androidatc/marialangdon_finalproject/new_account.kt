package com.androidatc.marialangdon_finalproject

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_account.*

class new_account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)

        auth = FirebaseAuth.getInstance()

        bt_create.setOnClickListener() {
            if (newEmail.text.trim().toString().isNotEmpty()
                || newPassword.text.trim().toString().isNotEmpty()
            ) {
                createUser(
                    newEmail.text.trim().toString(), newPassword.text.trim().toString()
                )
            }

            else{
                Snackbar.make(findViewById(R.id. bt_create),
                    "check your username and password then try again",
                Snackbar.LENGTH_LONG).show()
            }
        }

        // when user clicks on create account button they are taken to that activity
    }

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, success::class.java))
                }
                else {
                    Snackbar.make(
                        findViewById(R.id.bt_create),
                        "Enter a valid username and password (6 characters)",
                            Snackbar.LENGTH_LONG
                    ).show()
                }
            }
    }
}