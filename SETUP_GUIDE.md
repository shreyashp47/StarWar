# Setup Guide - Star War Android App

## 🚀 Quick Start

Follow these steps to get the Star War app running on your local machine.

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio** (Arctic Fox or later)
- **JDK 8** or higher
- **Android SDK** with API level 24+
- **Git** for version control

## 🛠️ Installation Steps

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/StarWar.git
cd StarWar
```

### 2. Open in Android Studio
1. Launch Android Studio
2. Select **"Open an existing project"**
3. Navigate to the cloned `StarWar` directory
4. Click **"OK"**

### 3. Sync Project
1. Android Studio will automatically detect the Gradle files
2. Click **"Sync Now"** when prompted
3. Wait for the sync to complete (this may take a few minutes)

### 4. Configure SDK
1. Go to **File → Project Structure**
2. Under **Project**, ensure:
   - **Compile SDK Version**: API 34
   - **Build Tools Version**: Latest available
3. Under **Modules → app**, verify:
   - **Compile SDK Version**: API 34
   - **Min SDK Version**: API 24
   - **Target SDK Version**: API 34

## 📱 Running the App

### Option 1: Physical Device
1. Enable **Developer Options** on your Android device:
   - Go to **Settings → About Phone**
   - Tap **Build Number** 7 times
2. Enable **USB Debugging**:
   - Go to **Settings → Developer Options**
   - Toggle **USB Debugging** ON
3. Connect your device via USB
4. Click the **Run** button (▶️) in Android Studio
5. Select your device from the list

### Option 2: Android Emulator
1. Open **AVD Manager** (Tools → AVD Manager)
2. Click **"Create Virtual Device"**
3. Choose a device (recommended: Pixel 4 or newer)
4. Select a system image (API 24 or higher)
5. Click **"Finish"** to create the AVD
6. Start the emulator
7. Click the **Run** button (▶️) in Android Studio

## 🔧 Configuration

### API Endpoints
The app uses JSONBin.io for data. The endpoints are already configured:
- Users: `https://api.jsonbin.io/b/IKQQ`
- Matches: `https://api.jsonbin.io/b/JNYL`

### Network Permissions
The app requires internet access. This is already configured in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## 🐛 Troubleshooting

### Common Issues and Solutions

#### 1. Gradle Sync Failed
**Problem**: Gradle sync fails with dependency errors
**Solution**: 
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

#### 2. SDK Not Found
**Problem**: Android SDK path not configured
**Solution**:
1. Go to **File → Project Structure → SDK Location**
2. Set the correct Android SDK path
3. Apply changes

#### 3. Build Failed - Missing Dependencies
**Problem**: Dependencies not downloaded
**Solution**:
1. Check internet connection
2. Go to **File → Sync Project with Gradle Files**
3. Wait for sync to complete

#### 4. App Crashes on Launch
**Problem**: Runtime crashes
**Solution**:
1. Check **Logcat** for error messages
2. Ensure minimum API level is 24+
3. Verify internet connectivity for API calls

#### 5. No Data Displayed
**Problem**: Empty leaderboard
**Solution**:
1. Check internet connection
2. Verify API endpoints are accessible
3. Check **Logcat** for network errors

### Debug Mode
To enable detailed logging:
1. Open `MatchPointsActivity.kt`
2. Look for `Log.d()` and `Log.e()` statements
3. Monitor **Logcat** in Android Studio

## 📁 Project Structure

```
StarWar/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/shreyash/github/starwar/
│   │   │   │   ├── Activity/
│   │   │   │   ├── data/
│   │   │   │   ├── retrofit/
│   │   │   │   └── viewmodel/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔄 Development Workflow

### Making Changes
1. Create a new branch for your feature:
   ```bash
   git checkout -b feature/your-feature-name
   ```
2. Make your changes
3. Test thoroughly
4. Commit your changes:
   ```bash
   git add .
   git commit -m "Add your feature description"
   ```
5. Push to your branch:
   ```bash
   git push origin feature/your-feature-name
   ```

### Code Style
- Follow **Kotlin coding conventions**
- Use **meaningful variable names**
- Add **comments** for complex logic
- Keep **functions small** and focused

## 📚 Additional Resources

- [Android Developer Documentation](https://developer.android.com/)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Material Design Guidelines](https://material.io/design)

## 🆘 Getting Help

If you encounter issues:

1. **Check this guide** for common solutions
2. **Search existing issues** on GitHub
3. **Create a new issue** with:
   - Detailed description
   - Steps to reproduce
   - Error logs from Logcat
   - Your environment details

## ✅ Verification Checklist

Before submitting or deploying:

- [ ] App builds successfully
- [ ] All tests pass
- [ ] App runs on physical device
- [ ] App runs on emulator
- [ ] Network calls work properly
- [ ] UI displays correctly
- [ ] Sorting functionality works
- [ ] No memory leaks detected
- [ ] Code follows style guidelines

---

**Happy Coding! 🚀**

*If you found this setup guide helpful, please consider giving the project a star ⭐*