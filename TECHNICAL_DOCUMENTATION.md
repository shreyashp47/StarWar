# Technical Documentation - Star War App

## 📋 Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Data Models](#data-models)
3. [API Integration](#api-integration)
4. [Business Logic](#business-logic)
5. [UI Components](#ui-components)
6. [Data Persistence](#data-persistence)
7. [Performance Considerations](#performance-considerations)

## 🏗️ Architecture Overview

The application follows a **Model-View-ViewModel (MVVM)** architecture pattern with clear separation of concerns:

### Layer Structure
```
┌─────────────────────────────────────┐
│              UI Layer               │
│  (Activities, Adapters, Views)      │
├─────────────────────────────────────┤
│           ViewModel Layer           │
│     (Business Logic, State)         │
├─────────────────────────────────────┤
│            Data Layer               │
│  (Repository, API, Local Storage)   │
└─────────────────────────────────────┘
```

## 📊 Data Models

### Users Model
```kotlin
data class Users(
    val id: Int,
    val name: String,
    val avatar: String?,
    var points: Int = 0
)
```

### Player Model
```kotlin
data class Player(
    val id: Int,
    val score: Int
)
```

### Matches Model
```kotlin
data class Matches(
    val id: Int,
    val player1: Player?,
    val player2: Player?
)
```

## 🌐 API Integration

### Retrofit Configuration
```kotlin
object RetrofitClient {
    private const val BASE_URL = "https://api.jsonbin.io/"
    
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
```

### API Endpoints
- **GET /b/IKQQ** - Fetches list of users
- **GET /b/JNYL** - Fetches list of matches

### Error Handling
```kotlin
override fun onFailure(call: Call<List<Users>>, t: Throwable) {
    Log.e("MainActivity", "Error fetching users", t)
    // Handle network errors gracefully
}
```

## 🧮 Business Logic

### Point Calculation Algorithm

The app implements a tournament-style point system:

```kotlin
private fun calculatePoints(match: Matches) {
    when {
        match.player1?.score!! > match.player2?.score!! -> {
            // Player 1 wins: +3 points
            usersMap[match.player1?.id]?.points += 3
        }
        match.player2?.score!! > match.player1?.score!! -> {
            // Player 2 wins: +3 points
            usersMap[match.player2?.id]?.points += 3
        }
        else -> {
            // Draw: +1 point each
            usersMap[match.player1?.id]?.points += 1
            usersMap[match.player2?.id]?.points += 1
        }
    }
}
```

### Sorting Algorithms

#### Descending Sort (High to Low)
```kotlin
fun sortMapDescending(map: HashMap<Int, Users>): Map<Int, Users> {
    return map.entries
        .sortedByDescending { it.value.points }
        .associate { it.key to it.value }
}
```

#### Ascending Sort (Low to High)
```kotlin
fun sortMapAscending(map: HashMap<Int, Users>): Map<Int, Users> {
    return map.entries
        .sortedBy { it.value.points }
        .associate { it.key to it.value }
}
```

## 🎨 UI Components

### RecyclerView Implementation

#### PointsAdapter
```kotlin
class PointsAdapter(private val userList: List<Users>) : 
    RecyclerView.Adapter<PointsAdapter.ViewHolder>() {
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ViewHolder implementation
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate item layout
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to views
    }
}
```

### Dynamic Sorting Button
```kotlin
btn.setOnClickListener {
    if (btn.tag.toString() == "0") {
        // Sort High to Low
        val sortedMap = sortMapDescending(usersMap)
        updateRecyclerView(sortedMap)
        btn.tag = 1
        btn.text = "Low To High"
    } else {
        // Sort Low to High
        val sortedMap = sortMapAscending(usersMap)
        updateRecyclerView(sortedMap)
        btn.tag = 0
        btn.text = "High To Low"
    }
}
```

## 💾 Data Persistence

### SharedPreferences Implementation
```kotlin
object SharedPreferencesUtils {
    fun storeHashMap(context: Context, map: HashMap<Int, Users>) {
        val sharedPrefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(map)
        editor.putString("users_map", json)
        editor.apply()
    }
    
    fun retrieveHashMap(context: Context): HashMap<Int, Users>? {
        val sharedPrefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val json = sharedPrefs.getString("users_map", null)
        return if (json != null) {
            val gson = Gson()
            val type = object : TypeToken<HashMap<Int, Users>>() {}.type
            gson.fromJson(json, type)
        } else null
    }
}
```

## ⚡ Performance Considerations

### Memory Management
- **ViewHolder Pattern**: Efficient RecyclerView scrolling
- **Image Caching**: Glide handles bitmap caching automatically
- **Data Structure**: HashMap for O(1) user lookup by ID

### Network Optimization
- **Retrofit**: Efficient HTTP client with connection pooling
- **Gson**: Fast JSON parsing and serialization
- **Error Handling**: Graceful degradation on network failures

### UI Performance
- **RecyclerView**: Only renders visible items
- **Data Binding**: Minimal view lookups with findViewById
- **Background Processing**: Network calls on background threads

## 🔧 Configuration Options

### Build Configuration
```kotlin
android {
    compileSdk = 34
    
    defaultConfig {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(...)
        }
    }
}
```

### Network Security
```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.INTERNET" />
<application
    android:usesCleartextTraffic="true"
    android:hardwareAccelerated="true"
    android:largeHeap="true">
```

## 🧪 Testing Strategy

### Unit Tests
- Point calculation logic
- Sorting algorithms
- Data model validation

### Integration Tests
- API response parsing
- SharedPreferences operations
- RecyclerView adapter functionality

### UI Tests
- Button click interactions
- RecyclerView scrolling
- Data display accuracy

## 🚀 Deployment Considerations

### Release Build
```kotlin
buildTypes {
    release {
        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}
```

### ProGuard Rules
```proguard
# Keep data models for Gson
-keep class com.shreyash.github.starwar.data.** { *; }

# Keep Retrofit interfaces
-keep interface com.shreyash.github.starwar.retrofit.** { *; }
```

## 📈 Future Enhancements

1. **Database Integration**: Room database for complex queries
2. **Offline Mode**: Complete offline functionality
3. **Real-time Updates**: WebSocket integration
4. **User Authentication**: Login/logout functionality
5. **Match Details**: Detailed match view screen
6. **Statistics**: Advanced analytics and charts
7. **Push Notifications**: Match result notifications

---

*This technical documentation provides comprehensive insights into the Star War app's architecture, implementation details, and best practices followed during development.*