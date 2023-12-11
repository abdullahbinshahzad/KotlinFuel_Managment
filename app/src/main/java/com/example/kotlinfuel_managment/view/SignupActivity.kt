package com.example.kotlinfuel_managment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfuel_managment.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupBTN.setOnClickListener {
            val signupUserName = binding.signupUsernameET.text.toString()
            val signupPassword = binding.signupPasswordET.text.toString()
            val signupConfirmPassword = binding.signupConfirmPasswordET.text.toString()

            if (signupUserName.isNotEmpty() && signupPassword.isNotEmpty() && signupConfirmPassword.isNotEmpty()) {
                if (signupPassword == signupConfirmPassword){

                firebaseAuth.createUserWithEmailAndPassword(signupUserName, signupPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this@SignupActivity, "Authentication successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this@SignupActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            } else{
                Toast.makeText(this, "PLZ FILL SIGN-UP COMPLETELY", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginTV.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }
}