#!/bin/bash

# Find the jar file dynamically
JAR_FILE=$(find target -type f -name "*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
  echo "❌ No JAR file found in /app/target"
  exit 1
fi

echo "🚀 Running: java -jar $JAR_FILE"
exec java -jar "$JAR_FILE"
