# User Registration Service

This Service supports user sign up, sign in, view user details and search user by email.

## Tech Stack
* Spring boot
* Java 8
* Mysql 

## API's
Refer to Swagger after starting service.
* POST /api/auth/sign-in - User sign in.
* POST /api/auth/signup - User sign up.
* GET /api/user/_search search for user details based on email id.
* GET /api/user/{user-id} get user details

For details info refer to swagger.
http://localhost:6868/swagger-ui.html#/
 
## Build instructions 

* Download Unzip the file.
* switch to the root folder
```
        cd registration-service
```
* Build app using docker.
```
        docker-compose up -d
```
* Ensure mysql and registration containers are up .
```
        Starting registration-service_mysqldb_1 ... done
        Recreating registration-service_app_1   ... done
```
```
60442afcb1df        registration-service_app   "/usr/local/bin/mvn-…"   15 minutes ago      Up 15 minutes       0.0.0.0:6868->8080/tcp              registration-service_app_1
f2a33576061f        mysql:5.7                  "docker-entrypoint.s…"   45 minutes ago      Up 15 minutes       33060/tcp, 0.0.0.0:3307->3306/tcp   registration-service_mysqldb_1
```
* Confirm registration service is running on port 8080 (This is internal docker port).
```
app_1      | 2022-04-11 03:31:01.258  INFO 44 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
app_1      | 2022-04-11 03:31:01.261  INFO 44 --- [           main] d.s.w.p.DocumentationPluginsBootstrapper : Context refreshed
app_1      | 2022-04-11 03:31:01.283  INFO 44 --- [           main] d.s.w.p.DocumentationPluginsBootstrapper : Found 1 custom documentation plugin(s)
app_1      | 2022-04-11 03:31:01.344  INFO 44 --- [           main] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
app_1      | 2022-04-11 03:31:01.574  INFO 44 --- [           main] c.p.r.RegistrationServiceApplication     : Started RegistrationServiceApplication in 6.177 seconds (JVM running for 6.561)
```
* Test the service via swagger 
http://localhost:6868/swagger-ui.html#/

## Using the Service

* To sign up - Provide user name, password, last name , first name, phone number and email id. All fields are required. The service validates if there is no existing email id or user name before registration.
Sample request
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
 "emailId": "user@gmail.com", \ 
 "password": "pass", \ 
 "firstName": "sam", \ 
 "lastName": "john", \ 
 "userName": "sam", \ 
 "phoneNumber": "3434" \ 
 }' 'http://localhost:6868/api/auth/signup'
```
* To sign in - Provide user name and password. On a successful sign in, the service responses with a UUID and JWT token.
Sample request
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "userName": "sam", \ 
   "password": "pass" \ 
 }' 'http://localhost:6868/api/auth/sign-in'
```

* Get user details - This endpoint is not secured with JWT token validation. Provide UUID to get user details.
```
curl -X GET --header 'Accept: application/json' 'http://localhost:6868/api/user/a93ff818-2c69-4fb9-84c4-d613a6892be6'
```
* Search for user based on email id - This endpoint is not secured with JWT token validation. Provide email id to get user details.
```
curl -X GET --header 'Accept: application/json' 'http://localhost:6868/api/user/_search?emailId=user@gmail.com''
```
### Questions and Comments: sendurr@hotmail.com

