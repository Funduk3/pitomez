```
./gradlew bootRun
```

```
./gradlew test
```

server port: 8080

db: h2 only, will be made postgres not soon!

создать юзера
```
curl -X POST -H "Content-Type: application/json" -d '{"name":"Ivan","email":"ivan@example.com"}' http://localhost:8080/api/users
```


получить список
```
curl http://localhost:8080/api/users
```