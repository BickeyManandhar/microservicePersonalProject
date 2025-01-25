**Account Microservice**

Important notes:

**@RestController** : can be used to put on the top of a call. This will expose our methods as REST APIs.

**@Controller + @ResponseBody(Expecting JSON response)** : can be used together for same behaviour.

**ResponseEntity<T>** : Allow developers to send response body, status and headers on the HTTP response.

**@ControllerAdvice** : it is used to mak the class as a REST controller advice.
Along with @ExceptionHandler, this can be used to handle exceptions globally inside the app.

**@RestControllerAdvice** : This is the same as @ControllerAdvice + @ResponseBody

**RequestEntity<T>** : Allow developers to receive the request body, header in a HTTP request.

**@RequestHeader** and **@RequestBody** : is used to receive the request body and header individually.


**Pushing docker image created in the local to remote docker hub:**
docker image push docker.io/user_name/project_name:tag
docker image push docker.io/bickey007/accounts:v1

**To pull the image:**
docker pull docker.io/bickey007/accounts:v1

**To define and run multiple docker applications, we use Docker Compose:**
check docker-compose.yml inside accounts

**To run the services in the docker-compose.yml**
docker compose up -d

**To stop and delete the container**
docker compose down

**To stop and not delete**
docker compose stop

**To start the stopped service that was not deleted**
docker compose start

