# Sample User Service in Java Spring Boot

## Steps to build and create image

## Build and Run
```bash
docker build -t user-java-app:v1.0 .
```

## Run the app in docker container
```bash
docker run -e SPRING_DATASOURCE_URL="jdbc:mysql://192.168.1.4:3306/someorg" -p 8080:8080 user-java-app:v1.0
```

## Run test script
```bash
../test-someorg-api.sh
```
