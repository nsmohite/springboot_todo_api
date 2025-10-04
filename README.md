Run the app
From project root:

./mvnw spring-boot:run
# or
mvn spring-boot:run
App should start on http://localhost:8080/.



Test the API (curl)
List (empty)

curl http://localhost:8080/api/todos
# => []
Create

curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Buy milk","completed":false}'
Response: 201 Created and JSON with generated id.
List (now has one)

curl http://localhost:8080/api/todos
# => [{"id":1,"title":"Buy milk","completed":false}]
Get by id

curl http://localhost:8080/api/todos/1
Delete

curl -X DELETE http://localhost:8080/api/todos/1
