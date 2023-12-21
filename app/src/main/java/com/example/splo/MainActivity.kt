package com.example.splo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var Btn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Check if the user is not logged in
        if (!isLoggedIn()) {
            // If the user is not logged in, go back to the login activity
            goToLoginActivity()
        }

        Btn =findViewById(R.id.btnLogout)

        Btn.setOnClickListener {
            // Clear the login status
            setLoggedIn(false)
            // Go back to the login activity
            goToLoginActivity()
        }
    }

    private fun isLoggedIn(): Boolean {
        // Retrieve the login status from SharedPreferences
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false)
    }

    private fun setLoggedIn(isLoggedIn: Boolean) {
        // Save the login status to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putBoolean("IS_LOGGED_IN", isLoggedIn)
        editor.apply()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
