package com.androidatc.marialangdon_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_choose.*
import kotlinx.android.synthetic.main.activity_main.*

class choose : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        auth = FirebaseAuth.getInstance()

        // when user clicks on the entry button they are taken into the inventory activity to enter data into their list
        btAddEntry.setOnClickListener {
            startActivity(Intent(this, inventory::class.java))
        }

        // when user clicks on view inventory button they are taken into the inventory activity to view their list
        btViewInventory.setOnClickListener {
            startActivity(Intent(this, view_inventory::class.java))
        }

        // when user clicks on logout button they are signed out of the app and taken to the main menu
        btLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}