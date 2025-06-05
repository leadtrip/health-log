#!/bin/bash

set -e

echo "🚀 Starting Docker Compose for PostgreSQL..."
docker compose up -d

echo "⏳ Waiting for PostgreSQL to become healthy..."
until docker inspect --format='{{.State.Health.Status}}' healthlog-postgres 2>/dev/null | grep -q "healthy"; do
  printf '.'
  sleep 1
done

echo ""
echo "✅ PostgreSQL is healthy."

echo "🧱 Running Spring Boot app via Gradle..."
./gradlew bootRun
