from fastapi import FastAPI, Query # type: ignore
from fastapi.middleware.cors import CORSMiddleware # type: ignore
import requests # type: ignore
from typing import List
import os

app = FastAPI()

# CORS settings — allow mobile emulator access
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # In production, restrict this
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

GOOGLE_API_KEY = os.getenv("GOOGLE_API_KEY", "AIzaSyDv0NIE4f0Lb_9H-D0vp5sHOp9E5SQ6lsA")


@app.get("/plan/")
def generate_itinerary(
    city: str,
    days: int = 2,
    place_types: List[str] = Query(...),
    cuisine: List[str] = Query([])
):
    print(f"\n📥 Incoming Request: city={city}, days={days}, types={place_types}, cuisine={cuisine}")
    itinerary = []
    all_places = []

    try:
        # Step 1: Get coordinates
        geo_url = f"https://maps.googleapis.com/maps/api/geocode/json?address={city}&key={GOOGLE_API_KEY}"
        geo_res = requests.get(geo_url).json()
        if geo_res['status'] != 'OK':
            print("❌ Geocode Failed:", geo_res)
            return {"error": "City not found or Google API failed."}

        latlng = geo_res['results'][0]['geometry']['location']
        lat, lng = latlng['lat'], latlng['lng']
        print(f"📍 Location: {lat}, {lng}")

        # Step 2: Get nearby places
        for place_type in place_types:
            url = (
                f"https://maps.googleapis.com/maps/api/place/nearbysearch/json"
                f"?location={lat},{lng}&radius=8000&type={place_type}&key={GOOGLE_API_KEY}"
            )
            res = requests.get(url).json()
            print(f"🔎 Places for type={place_type} → found={len(res.get('results', []))}")
            for place in res.get("results", [])[:10]:
                all_places.append({
                    "name": place.get("name"),
                    "type": place_type,
                    "location": place.get("vicinity"),
                    "rating": place.get("rating", 0.0)
                })

        # Step 3: Distribute 2–3 per day
        daily_plan = [[] for _ in range(days)]
        for idx, place in enumerate(sorted(all_places, key=lambda x: -x["rating"])):
            day = idx % days
            if len(daily_plan[day]) < 3:
                daily_plan[day].append(place)

        # Step 4: Add hotel + restaurant
        for i in range(days):
            restaurants = get_nearby(lat, lng, "restaurant", cuisine)
            hotels = get_nearby(lat, lng, "lodging")

            itinerary.append({
                "day": i + 1,
                "places": daily_plan[i],
                "restaurant": restaurants[i % len(restaurants)] if restaurants else {"name": "No restaurants found"},
                "hotel": hotels[i % len(hotels)] if hotels else {"name": "No hotels found"},
            })

        print(f"✅ Generated Itinerary for {city}")
        return {
            "city": city,
            "days": days,
            "itinerary": itinerary
        }

    except Exception as e:
        print("❌ Server Error:", str(e))
        return {"error": "Internal Server Error", "details": str(e)}


# Reusable helper to fetch restaurants or hotels
def get_nearby(lat, lng, type_, filters: List[str] = []):
    try:
        url = (
            f"https://maps.googleapis.com/maps/api/place/nearbysearch/json"
            f"?location={lat},{lng}&radius=6000&type={type_}&key={GOOGLE_API_KEY}"
        )
        res = requests.get(url).json()
        results = []
        for place in res.get("results", [])[:10]:
            name = place.get("name", "")
            if filters and not any(f.lower() in name.lower() for f in filters):
                continue
            results.append({
                "name": name,
                "location": place.get("vicinity", ""),
                "rating": place.get("rating", 0.0)
            })
        return results
    except Exception as e:
        print(f"⚠️ Failed to fetch {type_}: {e}")
        return []
