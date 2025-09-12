import json
import random
import time
import uuid
import requests
from datetime import datetime, timezone

CATEGORIES = ["Gryff", "Slyth", "Raven", "Huff"]
API_URL = "http://localhost:8080/api/ingest"

def generate_record():
    return {
        "id": str(uuid.uuid4()),
        "category": random.choice(CATEGORIES),
        "points": random.randint(1, 100),
        "timestamp": datetime.now(timezone.utc).isoformat()
    }

def record_stream(delay_range=(0.5, 2)):
    while True:
        record = generate_record()
        try:
            requests.post(API_URL, json=record, timeout=5)
            print("Sent:", record)
        except Exception as e:
            print("Error:", e)
        time.sleep(random.uniform(*delay_range))

if __name__ == "__main__":
    record_stream()
