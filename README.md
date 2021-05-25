# oauth2

Securing rest endpoints using OAUTH2 tokens. The application also sends [actuator metrics](https://www.javatpoint.com/spring-boot-actuator#:~:text=Spring%20Boot%20Actuator%20is%20a,place%20where%20the%20resources%20live) to [Graphite db](https://graphiteapp.org/) which can be monitored using [Grafana](https://grafana.com/).

[![build pipeline](https://circleci.com/gh/ImRohitSingh/oauth2.svg?style=svg)](https://circleci.com/gh/ImRohitSingh/oauth2)

## Quick Access

* [Setting up Graphite db](https://github.com/ImRohitSingh/oauth2/blob/main/HELP.md#graphite)
* [Authorization Implementation Details](https://github.com/ImRohitSingh/oauth2#implementation)
* [Generating Oauth tokens](https://github.com/ImRohitSingh/oauth2#generating-oauth-tokens)
* [Validation Links](https://github.com/ImRohitSingh/oauth2#validation)

## Authorization Details

### Roles
  1. **USER**
  2. **ADMIN**

### Secured Credentials

Type | Purpose
----------  | -----------
user | for the role of **USER**
admin | for the role of **ADMIN**
client | to authorize the endpoints which will actually provide the **OAUTH** tokens

### Levels of Security

The endpoints have been categorized into *three* different **levels of security**:

Level | Purpose
----------  | -----------
public | can be accessed by any user either with credentials of the role of **ADMIN** or that of the role of  **USER**
private | can be accessed with **OAUTH tokens** generated for roles **USER** / **ADMIN**
admin | can be accessed with **OAUTH tokens** generated for role **ADMIN**

### Implementation

Config Type | Purpose
----------  | -----------
OAuth Configuration (AuthorizationServer) | binds the **client credential** to all *oauth* endpoints. Also, this will help in configuring the validity-time of **refresh_token** and **access_tokens**
Web Configuration (WebServer) | creates the two secured users and their associated roles
Resource Configuration (ResourceServer) | defines the permit level of all the endpoints

## Generating Oauth tokens

1. **access_token**
```sh
endpoint: /oauth/token
method: POST
request:
  Basic Authorization: client credentials
  x-www-form-urlencoded:
       password: <password for role USER/ADMIN> 
       username: <password for role USER/ADMIN>
       grant_type: password
response:
{
    "access_token": "39cad071-5e44-48cc-a62d-d46e14a5775b",
    "token_type": "bearer",
    "refresh_token": "a7bf11c9-4e99-4dce-8f1f-f895d75cf9b5",
    "expires_in": <configured_time_in_seconds>,
    "scope": "read write"
}
```

2. **access_token** using **refresh_token**
```sh
endpoint: /oauth/token
method: POST
request:
  Basic Authorization: client credentials
  x-www-form-urlencoded:
       password: <password for role USER/ADMIN> 
       username: <password for role USER/ADMIN>
       grant_type: refresh_token
       refresh_token: a7bf11c9-4e99-4dce-8f1f-f895d75cf9b5
response:
{
    "access_token": "97c2049f-b54c-40f7-9b3b-5b9971a2c820",
    "token_type": "bearer",
    "refresh_token": "a7bf11c9-4e99-4dce-8f1f-f895d75cf9b5",
    "expires_in": <configured_time_in_seconds>,
    "scope": "read write"
}
```

### Notes

In an ideal scenario:
> * **refresh_token** will have a longer validity, after which a new token can be generated using the first endpoint
> * **access_token** will have a much lesser validity, after which the second endpoint can be used to generate a new token

## Endpoint Details
endpoints | security type
----------  | -----------
"/public" | public
"/private/**" | private
"/admin/**" | admin
"/deployment" | no authorization needed

## Authorization Parameters in Request Details

1. Public endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Basic Authorization: "user" OR "admin" credentials
  ...
```

2. Private endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Bearer Token: "oauth token" generated for roles "USER" / "ADMIN"
  ...
```

3. Admin endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Bearer Token: "oauth token" generated for role "ADMIN"
  ...
```

## Validation

#### Context Link

* [oauth2](https://springoauth2template.herokuapp.com)

#### Test Link

* [Deployment Testing](https://springoauth2template.herokuapp.com/deployment)
