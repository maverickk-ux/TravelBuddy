
# 🌍 Travel Planner App

An intelligent Android travel planner app that generates personalized itineraries based on your city, preferences, budget, and travel dates. Automatically fetches top places, restaurants, and hotels, and creates a downloadable PDF itinerary with a mapped route.

---

## 📱 Features

- 🔐 **User Authentication** via Firebase
- 🏙️ **Trip Customization**: Enter city, dates, and budget
- 📍 **Place Selection**: Choose preferred categories (nature, adventure, historical, etc.)
- 🍽️ **Food Preferences**: Select cuisines, food type, and daily budget
- 🗺️ **Auto Itinerary Generation**: Based on Google Places + FastAPI backend
- 🗓️ **Day-wise Timeline View**: Includes tourist places, one restaurant, and a hotel per day
- 🗺️ **Static Route Map** with markers
- 📄 **PDF Generation** of your itinerary
- 📤 **Itinerary Sharing** (planned)

---

## ⚙️ Tech Stack

| Layer       | Tools Used                             |
|-------------|----------------------------------------|
| Frontend    | Android (Java), XML Layouts            |
| Backend     | FastAPI (Python)                       |
| APIs        | Google Places API, Google Static Maps  |
| Storage     | Firebase Auth, SQLite / SharedPrefs    |
| Networking  | Retrofit2, GSON                        |

---

## 🔧 Project Structure

```
app/
├── manifests/
│   └── AndroidManifest.xml
├── java/com.example.travelapp/
│   ├── auth/                  # Login/Register
│   ├── model/                 # ItineraryResponse, DayPlan, Place
│   ├── ui/                    # All screens (TripDetails, FoodPreference, etc.)
│   ├── utils/                 # RetrofitClient, ApiService
├── res/
│   ├── layout/                # activity_*.xml files
│   ├── drawable/              # App icons & illustrations
```

---

## 🚀 Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/yourusername/travel-planner-app.git
cd travel-planner-app
```

### 2. Add API Keys

- Open `res/values/strings.xml` and add your Google API Key:

```xml
<string name="google_maps_key">YOUR_GOOGLE_MAPS_API_KEY</string>
```

- Backend URL is set in `ApiClient.java`.

### 3. Setup Firebase

- Add your `google-services.json` in the `app/` folder
- Enable Email/Password auth in Firebase console

### 4. Run the App

- Open in Android Studio
- Sync Gradle and Run on emulator or device

---

## 📸 Screenshots

> Add screenshots/gifs of your screens here

| Trip Setup | Place Selection | Final Itinerary |
|------------|------------------|------------------|
| ![](screenshots/trip_details.png) | ![](screenshots/place_category.png) | ![](screenshots/itinerary_screen.png) |

---

## 📦 API Sample Response

```json
{
  "city": "Bangalore",
  "days": 2,
  "itinerary": [
    {
      "day": 1,
      "places": [...],
      "restaurant": {...},
      "hotel": {...}
    }
  ]
}
```

---

## 💡 Future Improvements

- 📍 GPS-based suggestions
- 🗣️ Multi-language support
- ⛺ Offline trip saving
- 🔄 Regenerate itinerary
- 👥 Share plans with friends

---

## 🧑‍💻 Author

**Shashank (Maverick)**  
IIIT Bangalore  
[LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/yourusername)

---

## 📝 License

This project is licensed under the [MIT License](LICENSE).
