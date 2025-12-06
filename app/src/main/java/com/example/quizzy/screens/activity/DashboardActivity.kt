package com.example.quizzy.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.quizzy.R
import com.example.quizzy.databinding.ActivityDashboardBinding
import com.example.quizzy.network.RetrofitClient.getDashboardData
import com.example.quizzy.screens.adapter.PerformanceAdapter
import com.example.quizzy.screens.adapter.QuizStreakAdapter
import com.example.quizzy.utility.dialogs.LoadingDialog
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var loading: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading = LoadingDialog(this)

        binding.rvQuizStreak.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvQuizStreak.setHasFixedSize(true)

        loadData()
    }

    fun loadData(){
        loading.show()
        lifecycleScope.launch {
            val response = getDashboardData()
            loading.dismiss()

            if (response.student != null){
                binding.tvHello.text = "Hello ${response.student.name}!"
                binding.tvClass.text = response.student.`class`
                binding.status.text = response.student.availability.status
                binding.quizAttempts.text = response.student.quiz.attempts.toString() + " Attempt"
                binding.accuracy.text = response.student.accuracy.current
                binding.tvMoodTitle.text = response.todaySummary.mood
                binding.tvMoodDesc.text = response.todaySummary.description
                binding.btnWatch.text = response.todaySummary.recommendedVideo.actionText

                // Image URL is invalid in the api for using this , please pass UR in the api
                val imageUrl = "https://firebasestorage.googleapis.com/v0/b/user-contacts-ade83.appspot.com/o/${response.todaySummary.characterImage}?alt=media"

                Glide.with(this@DashboardActivity)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_mascot)
                    .into(binding.ivMascot)

                val streakList = response.weeklyOverview.quizStreak
                binding.rvQuizStreak.adapter = QuizStreakAdapter(streakList)
                binding.progressAccuracy.progress = response.weeklyOverview.overallAccuracy.percentage
                binding.tvOverallAccuracy.text = response.weeklyOverview.overallAccuracy.label

                binding.rvPerformance.layoutManager =
                    LinearLayoutManager(this@DashboardActivity, LinearLayoutManager.HORIZONTAL, false)

                binding.rvPerformance.adapter = PerformanceAdapter(
                    response.weeklyOverview.performanceByTopic
                )

                binding.ivBell.setOnClickListener {
                    val intent = Intent(this@DashboardActivity, NotificationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}