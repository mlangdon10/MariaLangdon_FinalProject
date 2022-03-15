package com.androidatc.marialangdon_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()    {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            checkTheme()

            // when user clicks on create account button they are taken to that activity
            bt_signup.setOnClickListener {
                startActivity(Intent(this, new_account::class.java))
            }

            // when user clicks on login button they are taken to that activity
            bt_login.setOnClickListener {
                startActivity(Intent(this, login::class.java))
            }
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.preferences_menu, menu)
            menuInflater.inflate(R.menu.help, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.action_preferences -> {
                    val intent = Intent(this, Preferences::class.java)
                    startActivity(intent)
                }

            }
            when (item.itemId) {
                R.id.action_help -> {
                    val intent2 = Intent(this, help::class.java)
                    startActivity(intent2)
                }
            }

            return super.onOptionsItemSelected(item)
        }

    private fun checkTheme() {
        when (MyPreferences(this).darkMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
        }
    }

    }
