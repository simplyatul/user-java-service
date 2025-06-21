#!/bin/bash

set -o xtrace

BASE_URL="http://localhost:8080/someorg/v1"
EMAIL="ramsaraf@gmail.com"
ENC_EMAIL=$(echo "$EMAIL" | sed 's/@/%40/')

echo "👉 Creating user..."
curl -s -X POST "$BASE_URL/user" -H "Content-Type: application/json" -d '{
  "firstName": "Ram",
  "lastName": "Saraf",
  "emailId": "'"$EMAIL"'"
}'
echo -e "\n"

echo "👉 Fetching user..."
curl -s -X GET "$BASE_URL/user?email_id=$ENC_EMAIL"
echo -e "\n"

echo "👉 Adding education..."
curl -s -X POST "$BASE_URL/user/$ENC_EMAIL/education" -H "Content-Type: application/json" -d '{
  "instituteName": "Garware College",
  "degreeName": "MSc",
  "website": "https://mes.com",
  "startDate": "2010-08-01",
  "endDate": "2012-08-01",
  "isCurrent": false
}'
echo -e "\n"

echo "👉 Getting educations..."
curl -s -X GET "$BASE_URL/user/$ENC_EMAIL/education"
echo -e "\n"

echo "👉 Adding professional experience..."
curl -s -X POST "$BASE_URL/user/$ENC_EMAIL/professionalexp" -H "Content-Type: application/json" -d '{
  "orgName": "Yahoo",
  "position": "SWE",
  "startDate": "2012-08-01",
  "endDate": "2015-08-01",
  "isCurrent": false
}'
echo -e "\n"

echo "👉 Getting professional experiences..."
curl -s -X GET "$BASE_URL/user/$ENC_EMAIL/professionalexp"
echo -e "\n"

echo "👉 Deleting user..."
curl -s -X DELETE "$BASE_URL/user/$ENC_EMAIL"
echo -e "\n"

