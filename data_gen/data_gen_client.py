import json
import random
import time
import uuid
import requests
from datetime import datetime, timezone

API = "http://backend:8080/api/ingest"
CATEGORIES = ["Gryff", "Slyth", "Raven", "Huff"]

def generate_record():
    return {
        "id": str(uuid.uuid4()),
        "category": random.choice(CATEGORIES),
        "points": random.randint(1, 100),
        "timestamp": datetime.now(timezone.utc).isoformat()
    }

while True:
    record = generate_record()
    try:
        requests.post(API, json=record, timeout=5)
        print("Sent:", record)
    except Exception as e:
        print("Error sending record:", e)
    time.sleep(random.uniform(0.5, 2))
