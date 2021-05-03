# AWS Lambda using Spring Cloud Function

Demonstrates AWS Lambda in Java using Spring Cloud Function

## Running Locally

1) Run main method
2) Hit the endpoint using any HTTP client.
Example using curl:
```
curl --location --request POST 'http://localhost:5123/processPersonEvent' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "A123",
    "name": "John Goblin"
}'
```

## Running on AWS
1) Build this code using maven `mvn clean install`
2) Create a Lambda function on AWS
3) Upload the jar from target folder (one having 'aws' in its name)
4) Set main class in Lambda config as `com.smilep.aws.lambda.handler.PersonEventHandler` (or any other handler, check `handler` package)   
4) Create an ENV variable in AWS Lambda called `FUNCTION_NAME` with value `processPersonEvent` (name of the function bean). Check other available function beans in `FunctionsConfig.java`.
5) Send an event using test window on AWS Lambda console
