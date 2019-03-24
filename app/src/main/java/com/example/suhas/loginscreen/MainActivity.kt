package com.example.suhas.loginscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth= FirebaseAuth.getInstance();
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if(mAuth.currentUser!=null)
        {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }
    }
    fun change(v: View)
    {
        val i= Intent(this@MainActivity,SignUpActivity::class.java)
        startActivity(i);
    }
    fun signin(v: View) {
        val em = email.text.toString()
        val ps = password.text.toString()
        if (TextUtils.isEmpty(em)) {
            email.error = "Enter a valid email address"
        } else if (ps.isEmpty() || ps.length < 6 || ps.length >= 14) {
            password.error = "Password length must between 6 and 14 characters"
        } else {
            mAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Authentication Success", Toast.LENGTH_SHORT).show()
                        //Write the operation need to be performed here
                    } else {
                        Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}

