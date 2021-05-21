# oauth2

Securing rest endpoints using OAUTH2 tokens. The application also sends actuator metrics to [Graphite](https://github.com/ImRohitSingh/oauth2/blob/main/HELP.md#graphite) which can me monitored using [Grafana](https://grafana.com/).

## Authorization Details

There are two roles:
  1. **USER**
  2. **ADMIN**

And *three* type of **secured credentials**:

Type | Purpose
----------  | -----------
user | for the role of **USER**
admin | for the role of **ADMIN**
client | to authorize the endpoints which will actually provide the **OAUTH** tokens

The endpoints have been cateforized into *three* different **levels of security**:

Level | Purpose
----------  | -----------
public | can be accessed by any user either with credentials of the role of **ADMIN** or that of the role of  **USER**
private | can be accessed with **OAUTH tokens** generated for role **USER**
admin | can be accessed with **OAUTH tokens** generated for role **ADMIN**

Implements three **authorization** configuration classes:

Config Type | Purpose
----------  | -----------
OAuth Configuration (AuthorizationServer) | binds the **client credential** to all *oauth* endpoints. Also, this will help in configuring the validity-time of **refresh_token** and **access_tokens**
Web Configuration (WebServer) | creates the two secured users and their associated roles
Resource Configuration (ResourceServer) | defines the permit level of all the endpoints

## Generatin Oauth tokens

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
> * **refresh_token** will have a longer validity, after which a new token can be generated using the first endpoint.
> * **access_token** will have a much lesser validity, after which the second endpoint can be used to generate a new token.

## Endpoint Details
endpoints | security type
----------  | -----------
"/public" | public
"/private/**" | private
"/admin/**" | admin

## Authorization Parameters in Request Details

1. Public endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Basic Authorization: ```user``` OR ```admin``` credentials
  ...
```

2. Private endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Bearer Token: ```oauth token``` generated for roles "USER" / "ADMIN"
  ...
```

2. Admin endpoints
```sh
endpoint: /<endpoint>
method: <method type>
request:
  Bearer Token: ```oauth token``` generated for role "ADMIN"
  ...
```


