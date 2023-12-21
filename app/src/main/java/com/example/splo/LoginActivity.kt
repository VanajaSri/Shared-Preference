package com.example.splo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    lateinit var LoginButton : Button
    lateinit var Username : TextView
    lateinit var Password : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginButton = findViewById(R.id.btnLogin)
        Username = findViewById(R.id.etUsername)
        Password = findViewById(R.id.etPassword)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Check if the user is already logged in
        if (isLoggedIn()) {
            // If the user is already logged in, go to the main activity
            goToMainActivity()
        }
            LoginButton.setOnClickListener {
            val username = Username.text.toString().trim()
            val password = Password.text.toString().trim()

            // Simulate a simple login mechanism (you should implement your own authentication logic)
            if (isValidCredentials(username, password)) {
                // Save the login status
                setLoggedIn(true)

                // Go to the main activity
                goToMainActivity()
            } else {
                // Display an error message (you should handle this according to your app's requirements)
                // For simplicity, we'll just show a toast
                Toast.makeText(this,"Invalid credentials",Toast.LENGTH_LONG)
            }
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

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // Implement your authentication logic here
        // For simplicity, we'll just check if both fields are non-empty
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
