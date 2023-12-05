package com.example.kotlinfuel_managment.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kotlinfuel_managment.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginBTN.setOnClickListener {
            val loginusername = binding.loginUsernameET.text.toString()
            val loginpassword = binding.loginPasswordET.text.toString()

            if (loginusername.isNotEmpty() && loginpassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(loginusername, loginpassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                            Log.d("loginUserWithEmail:", "SUCCESSFUL")
                            Toast.makeText(this@LoginActivity, "Login successful.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                        // If sign in fails, display a message to the user.
                            Log.d("loginUserWithEmail:", "FAILURE")
                            Toast.makeText(this@LoginActivity, "Login failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else{
                Toast.makeText(this, "PLZ FILL THE EMPTY LOG-IN FIELDs", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signupTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }
    }
}