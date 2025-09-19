# Как запустить?

prod окружение (бд postgresql)
```
docker-compose up --build
```
dev окружение (бд h2)
```
 ./gradlew bootRun --args='--spring.profiles.active=dev'
 ```