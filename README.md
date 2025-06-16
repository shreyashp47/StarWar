# Star War - Android Leaderboard App 🏆

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![API](https://img.shields.io/badge/Min%20API-24-orange.svg)](https://developer.android.com/about/versions/nougat/android-7.0)
[![Time](https://img.shields.io/badge/Development%20Time-3%20Hours-red.svg)](https://github.com/yourusername/StarWar)
[![Status](https://img.shields.io/badge/Assignment-Approved-brightgreen.svg)](https://github.com/yourusername/StarWar)

> **Flipkart Assignment Project** - Completed in 3 hours, approved, and advanced to next interview round

<div align="center">
  <img src="https://via.placeholder.com/1200x630/1a1a1a/ffffff?text=Star+War+Android+App" alt="Star War App Banner" width="100%">
</div>

## 📱 Overview

Star War is an Android application that displays a dynamic leaderboard for players based on their match performance. The app fetches user data and match results from a remote API, calculates points based on match outcomes, and presents them in an interactive, sortable leaderboard format.

## ✨ Features

- **Dynamic Leaderboard**: Real-time calculation and display of player rankings
- **Sorting Options**: Toggle between High-to-Low and Low-to-High point sorting
- **Match Point System**: 
  - Winner gets 3 points
  - Draw gives 1 point to each player
  - Loser gets 0 points
- **Offline Storage**: Uses SharedPreferences for data persistence
- **Clean UI**: Material Design components with RecyclerView implementation
- **Network Integration**: Retrofit for API communication

## 🏗️ Architecture

The app follows a clean architecture pattern with separation of concerns:

```
├── Activity/
│   ├── MatchPointsActivity.kt      # Main leaderboard screen
│   └── UsersMatchesActivity.kt     # User matches detail screen
├── data/
│   ├── Users.kt                    # User data model
│   ├── Player.kt                   # Player data model
│   └── Matches.kt                  # Match data model
├── retrofit/
│   ├── ApiService.kt               # API endpoints interface
│   └── RetrofitClient.kt           # Retrofit configuration
├── viewmodel/
│   └── BaseViewModel.kt            # Base ViewModel class
├── PointsAdapter.kt                # RecyclerView adapter for leaderboard
├── MatchesAdapter.kt               # RecyclerView adapter for matches
└── SharedPreferencesUtils.kt       # Local storage utility
```

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Android Views with Material Design
- **Networking**: Retrofit 2.9.0 + Gson
- **Architecture**: MVVM pattern
- **Storage**: SharedPreferences
- **Image Loading**: Glide 4.12.0
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

## 📋 Dependencies

```kotlin
// Core Android
implementation("androidx.core:core-ktx")
implementation("androidx.appcompat:appcompat")
implementation("com.google.android.material:material")
implementation("androidx.constraintlayout:constraintlayout")

// Networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.google.code.gson:gson:2.8.9")

// Image Loading
implementation("com.github.bumptech.glide:glide:4.12.0")

// Lifecycle
implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 24+
- Kotlin 1.8+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/StarWar.git
   cd StarWar
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Build and Run**
   - Sync project with Gradle files
   - Connect an Android device or start an emulator
   - Click "Run" or press `Ctrl+R`

## 📱 App Flow

1. **Launch**: App starts with `MatchPointsActivity`
2. **Data Fetching**: 
   - Fetches users from API endpoint `/b/IKQQ`
   - Fetches matches from API endpoint `/b/JNYL`
3. **Point Calculation**: Processes match results and calculates points
4. **Display**: Shows sorted leaderboard with toggle sorting option
5. **Persistence**: Stores data locally using SharedPreferences

## 🎯 Key Algorithms

### Point Calculation Logic
```kotlin
// Winner gets 3 points
if (player1.score > player2.score) {
    player1.points += 3
} else if (player2.score > player1.score) {
    player2.points += 3
} else {
    // Draw: both players get 1 point
    player1.points += 1
    player2.points += 1
}
```

### Sorting Implementation
```kotlin
// Descending sort (High to Low)
fun sortMapDescending(map: HashMap<Int, Users>): Map<Int, Users> {
    return map.entries
        .sortedByDescending { it.value.points }
        .associate { it.key to it.value }
}
```

## 📸 Screenshots

<div align="center">
<table>
  <tr>
    <td align="center">
      <img src="https://github.com/shreyashp47/StarWar-flipkart-assignment-app/blob/master/SS/Screenshot_20250616_093736.png" alt="Main Leaderboard" width="200"/>
      <br><b><h3>🏆 Main Leaderboard Screen</h3></b>
    </td>
    <td align="center">
      <img src="https://github.com/shreyashp47/StarWar-flipkart-assignment-app/blob/master/ss/Screenshot_20250616_093755.png" alt="Match Details" width="200"/>
      <br><b><h3>📊 Match Details</h3></b>
    </td>
  </tr>
</table>
</div>


### 🎯 Key UI Features
- **Material Design**: Clean, modern interface following Google's design guidelines
- **Responsive Layout**: Optimized for different screen sizes and orientations
- **Smooth Animations**: Fluid transitions and interactions
- **Real-time Updates**: Dynamic content updates without app restart

> **Note**: Replace placeholder images with actual screenshots of your app by uploading images to your repository and updating the image URLs above.

## 🧪 Testing

The project includes:
- Unit test setup with JUnit
- Instrumented tests with Espresso
- Test coverage for core business logic

Run tests using:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## 🔧 Configuration

### API Endpoints
The app connects to external APIs. Update the base URL in `RetrofitClient.kt` if needed:

```kotlin
private const val BASE_URL = "https://your-api-base-url.com/"
```

### Network Security
- App allows cleartext traffic for development
- Internet permission is required
- Consider implementing certificate pinning for production

## 📈 Performance Optimizations

- **Efficient RecyclerView**: Uses ViewHolder pattern for smooth scrolling
- **Image Caching**: Glide handles image loading and caching
- **Data Persistence**: SharedPreferences for offline capability
- **Memory Management**: Proper lifecycle handling to prevent leaks

## 🤝 Contributing

This project was created as part of a Flipkart assignment. While it's primarily for showcase purposes, suggestions and improvements are welcome!

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is created for educational and assignment purposes. Feel free to use it as a reference for your own projects.

## 👨‍💻 Author

**Shreyash**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your LinkedIn Profile](https://linkedin.com/in/yourprofile)

## ⏱️ Development Timeline

**Total Development Time: 3 Hours** ⚡

This project was completed as a timed technical assignment with the following breakdown:
- **Planning & Architecture**: 30 minutes
- **Core Development**: 2 hours
- **Testing & Polish**: 30 minutes

### 🎯 Time Management Strategy
- **Rapid Prototyping**: Quick MVP development
- **Incremental Features**: Building core functionality first
- **Efficient Debugging**: Strategic use of logging and testing
- **Clean Code**: Writing maintainable code under time pressure

## 🏆 Assignment Status

✅ **Project Completed in 3 Hours**  
✅ **Project Approved by Flipkart**  
✅ **Advanced to Next Interview Round**

### 📋 Assignment Requirements Met
- [x] **Time Constraint**: Completed within 3-hour limit
- [x] **Functionality**: All required features implemented
- [x] **Code Quality**: Clean, readable, and well-structured
- [x] **UI/UX**: Professional and user-friendly interface
- [x] **API Integration**: Successful data fetching and processing
- [x] **Error Handling**: Robust error management
- [x] **Performance**: Optimized for smooth user experience

## 🎨 Social Media & Sharing

### Open Graph Meta Tags
When sharing this repository on social media, the following preview will be displayed:

```html
<meta property="og:title" content="Star War - Android Leaderboard App" />
<meta property="og:description" content="Flipkart Assignment: Android app with dynamic leaderboard, completed in 3 hours and approved for next interview round" />
<meta property="og:image" content="https://via.placeholder.com/1200x630/1a1a1a/ffffff?text=Star+War+Android+App" />
<meta property="og:url" content="https://github.com/yourusername/StarWar" />
<meta property="og:type" content="website" />
```

### Twitter Card
```html
<meta name="twitter:card" content="summary_large_image" />
<meta name="twitter:title" content="Star War - Android Leaderboard App" />
<meta name="twitter:description" content="Completed Flipkart Android assignment in 3 hours - Dynamic leaderboard with API integration" />
<meta name="twitter:image" content="https://via.placeholder.com/1200x630/1a1a1a/ffffff?text=Star+War+Android+App" />
```

### LinkedIn Sharing
Perfect for showcasing your technical skills:
> "Just completed a challenging Android development assignment for Flipkart! Built a dynamic leaderboard app with API integration, real-time data processing, and smooth UI - all within a 3-hour time constraint. Proud to have been approved and advanced to the next interview round! 🚀 #AndroidDevelopment #Kotlin #TechInterview #Flipkart"

---

*This project demonstrates proficiency in Android development, API integration, data management, and clean code practices under time pressure - exactly what's required for fast-paced development environments.*