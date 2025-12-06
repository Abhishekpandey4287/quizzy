package com.example.quizzy.data.model

data class StudentDashboard(
    val student: Student,
    val todaySummary: TodaySummary,
    val weeklyOverview: WeeklyOverview
)

data class Student(
    val name: String,
    val `class`: String,
    val availability: Availability,
    val quiz: Quiz,
    val accuracy: Accuracy
)

data class Availability(val status: String)
data class Quiz(val attempts: Int)
data class Accuracy(val current: String)

data class TodaySummary(
    val mood: String,
    val description: String,
    val recommendedVideo: RecommendedVideo,
    val characterImage: String?
)

data class RecommendedVideo(val title: String, val actionText: String)

data class WeeklyOverview(
    val quizStreak: List<StreakItem>,
    val overallAccuracy: OverallAccuracy,
    val performanceByTopic: List<PerformanceTopic>
)

data class StreakItem(val day: String, val status: String)
data class OverallAccuracy(val percentage: Int, val label: String)
data class PerformanceTopic(val topic: String, val trend: String)