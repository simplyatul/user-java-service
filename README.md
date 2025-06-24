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
## Tag the image
```bash
docker tag user-java-app:v1.0 atulthosar/user-java-app:v1.0
```
## Push image to docker hub
Assuming docker hub account id is ```atulthosar```
```bash
docker push atulthosar/user-java-app:v1.0
```
