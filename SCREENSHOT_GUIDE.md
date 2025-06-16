# Screenshot Guide 📸

This guide will help you capture and organize screenshots for your Star War Android app to make it more visible and professional on GitHub.

## 📱 Screenshots to Capture

### 1. Main Leaderboard Screen
- **What to show**: The main screen with the leaderboard displaying users and their points
- **Key elements**: RecyclerView with user list, sorting button, app title
- **Filename**: `screenshot_leaderboard_main.png`

### 2. Sorting Functionality
- **What to show**: The same screen but with different sorting (High-to-Low vs Low-to-High)
- **Key elements**: Button showing "High To Low" or "Low To High", reordered list
- **Filename**: `screenshot_sorting_feature.png`

### 3. User Matches Screen
- **What to show**: The second activity showing user matches
- **Key elements**: Match details, user information, navigation
- **Filename**: `screenshot_user_matches.png`

### 4. Loading State (Optional)
- **What to show**: App while loading data from API
- **Key elements**: Loading indicators, empty state
- **Filename**: `screenshot_loading_state.png`

## 🛠️ How to Take Screenshots

### Method 1: Android Studio (Recommended)
1. Run your app in the emulator
2. Navigate to the screen you want to capture
3. In Android Studio, go to **View → Tool Windows → Logcat**
4. Click the **camera icon** 📷 in the emulator toolbar
5. Save the screenshot with the appropriate filename

### Method 2: Physical Device
1. Run the app on your physical device
2. Navigate to the desired screen
3. Take screenshot using device buttons:
   - **Most Android**: Power + Volume Down
   - **Samsung**: Power + Home (older) or Power + Volume Down
4. Transfer screenshots to your computer

### Method 3: ADB Command
```bash
# Connect your device and run:
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png ./screenshot.png
```

## 📁 Organizing Screenshots

### 1. Create Screenshots Directory
```bash
mkdir screenshots
```

### 2. Recommended File Structure
```
StarWar/
├── screenshots/
│   ├── screenshot_leaderboard_main.png
│   ├── screenshot_sorting_feature.png
│   ├── screenshot_user_matches.png
│   ├── screenshot_loading_state.png
│   └── app_banner.png (optional custom banner)
├── README.md
└── ...
```

### 3. Optimal Image Specifications
- **Resolution**: 1080x1920 (or your device's native resolution)
- **Format**: PNG (for better quality) or JPG
- **Size**: Keep under 1MB each for faster loading
- **Aspect Ratio**: 9:16 (portrait) for mobile screenshots

## 🎨 Creating a Custom Banner

### Option 1: Use Canva (Recommended)
1. Go to [Canva.com](https://canva.com)
2. Create a new design with dimensions **1200x630px**
3. Use the template:
   - Background: Dark theme (#1a1a1a)
   - Title: "Star War Android App"
   - Subtitle: "Flipkart Assignment - Completed in 3 Hours"
   - Add Android/Kotlin icons
   - Include app screenshots as mockups

### Option 2: Use Figma
1. Create a new frame (1200x630px)
2. Design your banner with:
   - App name and description
   - Technology badges
   - Phone mockups with screenshots
   - Professional color scheme

### Option 3: Simple Text Banner
Use the existing placeholder and customize:
```
https://via.placeholder.com/1200x630/1a1a1a/ffffff?text=Star+War+Android+App+%7C+Flipkart+Assignment
```

## 📝 Updating README with Screenshots

Once you have your screenshots, update the README.md:

### 1. Upload Screenshots to Repository
```bash
git add screenshots/
git commit -m "Add app screenshots"
git push origin main
```

### 2. Update Image URLs in README
Replace the placeholder URLs with your actual screenshot URLs:

```markdown
<!-- Replace this -->
<img src="https://via.placeholder.com/300x600/2196F3/ffffff?text=Leaderboard+Screen" alt="Leaderboard Screen" width="250">

<!-- With this -->
<img src="./screenshots/screenshot_leaderboard_main.png" alt="Leaderboard Screen" width="250">
```

### 3. Update Banner Image
```markdown
<!-- Replace this -->
<img src="https://via.placeholder.com/1200x630/1a1a1a/ffffff?text=Star+War+Android+App" alt="Star War App Banner" width="100%">

<!-- With this -->
<img src="./screenshots/app_banner.png" alt="Star War App Banner" width="100%">
```

## 🌟 Pro Tips for Better Screenshots

### 1. Clean UI State
- Ensure the app has data loaded (not empty states)
- Use realistic user names and scores
- Make sure UI elements are properly aligned

### 2. Consistent Styling
- Use the same device/emulator for all screenshots
- Maintain consistent lighting and contrast
- Keep the same orientation (portrait recommended)

### 3. Highlight Key Features
- Show the sorting button in different states
- Capture the leaderboard with varied scores
- Include any animations or transitions if possible

### 4. Multiple Devices (Optional)
- Take screenshots on different screen sizes
- Show tablet layout if supported
- Include both light and dark themes if available

## 🚀 Making Screenshots GitHub-Ready

### 1. Optimize File Sizes
```bash
# Using ImageOptim (Mac) or TinyPNG (Web)
# Compress images to reduce file size while maintaining quality
```

### 2. Add Alt Text
Always include descriptive alt text for accessibility:
```markdown
<img src="./screenshots/screenshot_leaderboard_main.png" alt="Star War app main leaderboard screen showing sorted user rankings with points" width="250">
```

### 3. Responsive Display
Use HTML for better control:
```html
<div align="center">
  <img src="./screenshots/screenshot_leaderboard_main.png" alt="Leaderboard Screen" width="250" style="margin: 10px;">
  <img src="./screenshots/screenshot_sorting_feature.png" alt="Sorting Feature" width="250" style="margin: 10px;">
</div>
```

## ✅ Screenshot Checklist

Before publishing your repository:

- [ ] All main screens captured
- [ ] Screenshots are clear and high-quality
- [ ] File sizes are optimized (< 1MB each)
- [ ] Images are uploaded to the repository
- [ ] README.md updated with correct image paths
- [ ] Alt text added for accessibility
- [ ] Banner image created and added
- [ ] All placeholder URLs replaced

---

**Remember**: Great screenshots can significantly increase the visibility and professionalism of your GitHub repository, making it more attractive to potential employers and collaborators! 📈