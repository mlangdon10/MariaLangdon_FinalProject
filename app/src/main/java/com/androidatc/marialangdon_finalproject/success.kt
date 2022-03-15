package com.androidatc.marialangdon_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_success.*

class success : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        // when user clicks on create account button they are taken to that activity
        bt_continue.setOnClickListener {
            startActivity(Intent(this, choose::class.java))
        }
    }

}