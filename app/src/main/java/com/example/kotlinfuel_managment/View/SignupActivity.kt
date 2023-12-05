package com.example.kotlinfuel_managment.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            val signupusername = binding.signupUsernameET.text.toString()
            val signuppassword = binding.signupPasswordET.text.toString()

            if (signupusername.isNotEmpty() && signuppassword.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(signupusername, signuppassword)
                    .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("createUserWithEmail:", "SUCCESSFUL")
                        Toast.makeText(this@SignupActivity, "Authentication successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("createUserWithEmail:", "FAILURE", task.exception)
                        Toast.makeText(this@SignupActivity, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else{
                Toast.makeText(this, "PLZ FILL THE EMPTY SIGN-UP FIELDs", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginTV.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }
}

//    private fun signupUser(username: String, password:String){
//        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object : ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (!dataSnapshot.exists()){
//                    val id = databaseReference.push().key
//                    val userData = User(id, username, password)
//                    databaseReference.child(id!!).setValue(userData)
//                    Toast.makeText(this@SignupActivity,"sign-up successfull", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this@SignupActivity, "User already exists", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Toast.makeText(this@SignupActivity, "Database Error ${databaseError.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }