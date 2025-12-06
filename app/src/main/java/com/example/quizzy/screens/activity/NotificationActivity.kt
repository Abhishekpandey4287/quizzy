package com.example.quizzy.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzy.databinding.ActivityNotificationBinding
import com.example.quizzy.utility.dialogs.LoadingDialog
import com.google.firebase.auth.FirebaseAuth

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var loading: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading = LoadingDialog(this)
        loading.show()

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
        loading.dismiss()

        binding.llLogout.setOnClickListener {
            loading.show()
            FirebaseAuth.getInstance().signOut()
            loading.dismiss()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}