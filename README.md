# CustomerService

## High level integration design

Use Spring Boot to build a microservice that containing APIs for CRUD operation on customer profile which consists of two eneites, customer profile and customer address. Token based authentication has been enabled to protect the APIs by using Spring Securiy Oauth2. Embedded H2 database has been used as the data store for this service. At the same time, embedded Tomcat server is used. For better performance, JVM parameter can be appended while running this application. 

## How to run the application
Download the source code, then cd into the project folder. Run "mvn clean package". Finally run "java -jar customer-service-0.0.1-SNAPSHOT.jar" under target folder. 

## Security aspect
Oauth2 has been applied to this service. the grant type used here is "client_credentials". The API user need to get the token with the credential specified in the yml file before making the call to other endpoints. 

## API Contract Definition
Swagger ui has been enabled. The url is http://localhost:8089/swagger-ui.html
Or you can use curl or postman to make the API call. Below is the sample of using curl to call API.

### 1. Get token:

Sample request:
```
curl -X POST \
  http://localhost:8089/oauth/token \
  -H 'authorization: Basic Y29yZS1zZXJ2aWNlOjA5NDBmNDg5MGNhMTM0M2NxMWowNw==' \
  -F grant_type=client_credentials
```

Sample result:
```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "authenticate",
    "jti": "214e33b0-ccc9-48e1-b4c2-a359bda24b26"
}
```
The value of access_token should be appened as the header to call other secured APIs


### 2. Get all customer profiles

Sample request:
```
curl -X GET \
  http://localhost:8089/customerProfiles/ \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo'
```

Sample result:
```
[
    {
        "customerProfileId": 1,
        "firstName": "Tom1",
        "lastName": "Tim1",
        "birthDay": "1987-02-21",
        "homeAddress": "home1",
        "officeAddress": "office1",
        "emailAddress": "tom1@pwc.com"
    }
]
```

### 3. Create a new customer profile

Sample request:
```
curl -X POST \
  http://localhost:8089/customerProfiles/ \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTQ0MzA4LCJqdGkiOiI5MjUzOGE2OC0yMmE2LTQ4ZDQtYWM0Yi0wZWRlMjBjODhlMjAiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.hkUtp7skV7HzPNhBjXc3ZLAEVFNYWpkBnvvuBSCb0SI' \
  -H 'content-type: application/json' \
  -d '{
	"firstName": "Tom1",
	"lastName": "Tim1",
	"dayOfBirth": 21,
	"monthOfBirth": 2,
	"yearOfBirth": 1987,
	"homeAddress": "home1",
	"officeAddress": "office1",
	"emailAddress": "tom1@pwc.com"
}'
```

Sample result:
```
{
    "customerProfileId": 1,
    "firstName": "Tom1",
    "lastName": "Tim1",
    "birthDay": "Sat Feb 21 08:00:00 CST 1987",
    "homeAddress": "home1",
    "officeAddress": "office1",
    "emailAddress": "tom1@pwc.com"
}
```

### 4. update a customer profile

Sample request:
```
curl -X PUT \
  http://localhost:8089/customerProfiles/1 \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo' \
  -H 'content-type: application/json' \
  -d '{
	"firstName": "Tom12",
	"lastName": "Tim12",
	"dayOfBirth": 1,
	"monthOfBirth": 12,
	"yearOfBirth": 1987,
	"homeAddress": "home12",
	"officeAddress": "office12",
	"emailAddress": "tom12@pwc.com"
}'
```

Sample result:
```
{
    "customerProfileId": 1,
    "firstName": "Tom12",
    "lastName": "Tim12",
    "birthDay": "Tue Dec 01 08:00:00 CST 1987",
    "homeAddress": "home12",
    "officeAddress": "office12",
    "emailAddress": "tom12@pwc.com"
}
```

### 5. Delete a customer profile 

Sample request:
```
curl -X DELETE \
  http://localhost:8089/customerProfiles/1 \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo'
```

Sample Result:
```
"deleted"
```

## TODO List
Address swagger being blocked by Oauth2.
Using lombok library to eliminate boil-plate code.
Asymmetric key-pair should be applied in terms of Oauth2. 
