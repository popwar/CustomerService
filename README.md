# CustomerService

High level integration design

Using Spring Boot to build the APIs for CRUD operation on customer profile which consists of two eneites, customer profile and address. 
Token based authentication has been enabled to protect the APIs by using Spring Securiy Oauth2.
Embedded H2 database has been used as the data store for this demo.


API Contract Definition (Swagger config has been added but, it is being blocked by Oauth2. Currently, working on that)

1. Get token:
curl -X POST \
  http://localhost:8089/oauth/token \
  -H 'authorization: Basic Y29yZS1zZXJ2aWNlOjA5NDBmNDg5MGNhMTM0M2NxMWowNw==' \
  -F grant_type=client_credentials
  
Sample result:
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "authenticate",
    "jti": "214e33b0-ccc9-48e1-b4c2-a359bda24b26"
}
The value of access_token should be appened as the header to call other secured APIs

2. Get all customer profiles
curl -X GET \
  http://localhost:8089/customerProfiles/ \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTM0OTExLCJqdGkiOiIyMTRlMzNiMC1jY2M5LTQ4ZTEtYjRjMi1hMzU5YmRhMjRiMjYiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.dFzYwDRAy0kxW4OhkO4DYKo3R6LCtEvFXRr29F6Babo'

Sample result:
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

3. Create a new customer profile
curl -X POST \
  http://localhost:8089/customerProfiles/ \
  -H 'authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY3VzdG9tZXItc2VydmljZSJdLCJzY29wZSI6WyJhdXRoZW50aWNhdGUiXSwiZXhwIjoxNTIxMTQ0MzA4LCJqdGkiOiI5MjUzOGE2OC0yMmE2LTQ4ZDQtYWM0Yi0wZWRlMjBjODhlMjAiLCJjbGllbnRfaWQiOiJjb3JlLXNlcnZpY2UifQ.hkUtp7skV7HzPNhBjXc3ZLAEVFNYWpkBnvvuBSCb0SI' \
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

Sample result:
{
    "customerProfileId": 1,
    "firstName": "Tom1",
    "lastName": "Tim1",
    "birthDay": "Sat Feb 21 08:00:00 CST 1987",
    "homeAddress": "home1",
    "officeAddress": "office1",
    "emailAddress": "tom1@pwc.com"
}
