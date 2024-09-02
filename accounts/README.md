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
