package com.example.kotlinfuel_managment.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.kotlinfuel_managment.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginBTN.setOnClickListener {
            val loginUserName = binding.loginUsernameET.text.toString()
            val loginPassword = binding.loginPasswordET.text.toString()

            if (loginUserName.isNotEmpty() && loginPassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(loginUserName, loginPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(this@LoginActivity, "Login successful.", Toast.LENGTH_SHORT).show()
                            val sharedPreference =  getSharedPreferences("Launcher", Context.MODE_PRIVATE)
                            var check : Boolean  = sharedPreference.getBoolean("flag", false)

                            if (check){
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                finish()
                                Toast.makeText(this@LoginActivity, "Main.", Toast.LENGTH_SHORT).show()
                            }else{
                                startActivity(Intent(this@LoginActivity, LauncherActivity::class.java))
                                finish()
                                Toast.makeText(this@LoginActivity, "Launcher.", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                        // If sign in fails, display a message to the user.
                            Toast.makeText(this@LoginActivity, "Login failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else{
                Toast.makeText(this, "PLZ FILL LOG-IN COMPLETELY", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signupTV.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }
    }
}