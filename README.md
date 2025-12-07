A simple Android application implementing a Login → Home → Logout flow with Firebase Authentication and API integration.

📱 Features
------------
Authentication: Firebase anonymous authentication (for demo purposes)
Home Screen: Displays student dashboard data from mock API
Dashboard: Shows student info, quiz streaks, performance metrics, and daily summary
Clean Architecture: MVVM pattern with Kotlin
Responsive UI: Follows provided Figma designs

🛠️ Prerequisites
-----------------
Android Studio (Latest stable version)
Kotlin 1.8+
Minimum SDK: API 21 (Android 5.0)
Firebase account for authentication


🔧 Setup Instructions
------------------------

1. Clone the Repository
bash
git clone <repository-url>
cd quizzy

3. Open in Android Studio
Open Android Studio
Select "Open an existing project"
Navigate to the cloned repository folder

4. Firebase Setup
Since the app uses Firebase Authentication, you need to:
Create a new project in Firebase Console
Add an Android app to your Firebase project:
Package name: com.example.quizzy
Download google-services.json
Place the downloaded google-services.json file in the app/ directory of the project

5. Build and Run
Sync project with Gradle files (File → Sync Project with Gradle Files)
Connect a physical device or start an emulator
Click "Run" button or use Shift + F10

📂 Project Structure
---------------------

quizzy/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/quizzy/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/          # Data classes and POJOs
│   │   │   │   │   └── repository/     # Data repositories
│   │   │   │   ├── screens/
│   │   │   │   │   ├── activity/       # Activities (Login, Dashboard, Notification)
│   │   │   │   │   ├── adapter/        # RecyclerView Adapters
│   │   │   │   │   └── viewmodel/      # ViewModels
│   │   │   │   ├── network/            # API service and Retrofit client
│   │   │   │   ├── utility/            # Helper classes, dialogs, extensions
│   │   │   │   └── di/                 # Dependency injection (if applicable)
│   │   │   ├── res/
│   │   │   │   ├── layout/             # XML layout files
│   │   │   │   ├── drawable/           # Images and vectors
│   │   │   │   ├── values/             # Colors, strings, styles
│   │   │   │   └── ...
│   │   │   └── AndroidManifest.xml
│   └── google-services.json            # Firebase config
├── build.gradle                        # App-level Gradle config
├── gradle.properties
└── settings.gradle

🔐 Authentication Flow
------------------------
Login Screen: Enter School ID and Student ID
Firebase Authentication: Uses anonymous sign-in for demo
Dashboard: After successful login, displays student data from API
Logout: Available in notification screen, clears authentication state

📊 API Integration
-------------------
The app fetches data from the mock API endpoint:

https://firebasestorage.googleapis.com/v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd

🚀 Building APK
------------------
Debug APK:
bash
./gradlew assembleDebug
Output: app/build/outputs/apk/debug/app-debug.apk

Release APK:
bash
./gradlew assembleRelease
Output: app/build/outputs/apk/release/app-release.apk

📝 Notes
------------
The current authentication uses Firebase anonymous sign-in for demonstration. In a production app, this should be replaced with proper authentication methods.
The character image URL in the API response might need adjustment based on actual image paths.
Error handling is implemented for network failures and authentication errors.
Loading dialogs are shown during async operations.

🧪 Testing
------------
Test with valid School ID and Student ID (any non-empty string works in current implementation)
Verify dashboard data loads correctly from API
Test logout functionality returns to login screen

