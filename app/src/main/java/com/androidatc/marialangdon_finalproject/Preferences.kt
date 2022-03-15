package com.androidatc.marialangdon_finalproject

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class Preferences : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        checkTheme()

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        if (key == "darkMode") {

            when (sharedPreferences?.getBoolean(key, false)) {
                false -> {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    MyPreferences(this).darkMode = 0
                }
                true -> {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    MyPreferences(this).darkMode = 1
                }
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

}
