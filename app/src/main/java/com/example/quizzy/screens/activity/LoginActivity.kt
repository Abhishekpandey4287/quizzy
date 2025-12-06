package com.example.quizzy.screens.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzy.databinding.ActivityLoginBinding
import com.example.quizzy.utility.dialogs.LoadingDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading = LoadingDialog(this)

        binding.tvSignin.setOnClickListener {
            val schoolId = binding.tilSchoolId.toString().trim()
            val studentId = binding.tilStudentId.toString().trim()
            if (schoolId.isEmpty() || studentId.isEmpty()) {
                Toast.makeText(this, "Please enter School & Student ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loading.show()
            Firebase.auth.signInAnonymously().addOnCompleteListener { task ->
                loading.dismiss()
                if (task.isSuccessful) {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}