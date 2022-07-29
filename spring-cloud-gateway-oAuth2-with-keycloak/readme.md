# Spring Cloud Gateway OAuth2 with Keycloak

- Ref: https://piotrminkowski.com/2020/10/09/spring-cloud-gateway-oauth2-with-keycloak/

<img width="1039" alt="Screenshot 2022-07-29 at 8 05 54 PM" src="https://user-images.githubusercontent.com/54174687/181789365-3a17220b-4bd6-438d-b67e-6ff281b6e0d6.png">

<img width="1289" alt="Screenshot 2022-07-29 at 8 07 15 PM" src="https://user-images.githubusercontent.com/54174687/181789393-0090da48-c4fc-4063-8ded-d105632c713b.png">

- The client `spring-with-test-scope` will have the scope TEST assigned. In contrast, the second client `spring-without-test-scope` will not have the scope TEST assigned.


<img width="889" alt="Screenshot 2022-07-29 at 8 39 23 PM" src="https://user-images.githubusercontent.com/54174687/181789854-2dcc37b3-98a3-4143-b255-eb22a39e795a.png">
<img width="796" alt="Screenshot 2022-07-29 at 8 39 40 PM" src="https://user-images.githubusercontent.com/54174687/181789896-1c55b07d-6b75-45d3-a1d9-87085003142a.png">
<img width="1002" alt="Screenshot 2022-07-29 at 8 39 53 PM" src="https://user-images.githubusercontent.com/54174687/181789914-ae33aa09-9311-4d8c-aa32-542836043cc8.png">
<img width="1000" alt="Screenshot 2022-07-29 at 8 40 14 PM" src="https://user-images.githubusercontent.com/54174687/181789932-ce96b7ef-89cf-4b1c-975a-0fbd706ce7cd.png">
<img width="673" alt="Screenshot 2022-07-29 at 8 41 25 PM" src="https://user-images.githubusercontent.com/54174687/181789947-efabc62d-9324-4c5e-a79e-1e6b11fb2b3b.png">

Log into gateway service
Now you can log into gateway service Keycloak web UI at http://localhost:8060/ user : prateek, password: prateek - Note - You can create password & set it.

<img width="521" alt="Screenshot 2022-07-29 at 8 44 40 PM" src="https://user-images.githubusercontent.com/54174687/181790662-8d96cfd2-e32d-439c-9f7d-a9bdc9aa208a.png">
<img width="575" alt="Screenshot 2022-07-29 at 8 45 13 PM" src="https://user-images.githubusercontent.com/54174687/181790677-ccbb1cf2-89b8-452e-8f0e-1a33c57475de.png">
<img width="355" alt="Screenshot 2022-07-29 at 8 45 25 PM" src="https://user-images.githubusercontent.com/54174687/181790691-4aa75220-a6b2-446f-936c-6435349d21fe.png">

- gateway-service

application.yml

```sh
server:
  port: 8060

spring:
  application:
    name: gateway-service
    
    
  cloud:
    gateway:
      default-filters:
      - TokenRelay
      routes:
      - id: callme-service
        uri: http://localhost:8040
        predicates:
        - Path=/callme/**
        filters:
        - RemoveRequestHeader=Cookie
        
        
  security:
    oauth2:
      client:
        provider:
          keycloak:
            token-uri: http://localhost:8080/realms/oauth2-demo-realm/protocol/openid-connect/token
            authorization-uri: http://localhost:8080/realms/oauth2-demo-realm/protocol/openid-connect/auth
            user-info-uri: http://localhost:8080/realms/oauth2-demo-realm/protocol/openid-connect/userinfo
            user-name-attribute: preferred_username
        registration:
          keycloak-with-test-scope:
            provider: keycloak
            client-id: spring-with-test-scope
            client-secret: XT6k41EmxvLiL732PZeLtlaOM1WRLId6
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/keycloak"
          keycloak-without-test-scope:
            provider: keycloak
            client-id: spring-without-test-scope
            client-secret: S0X5mQxucvZa6Q4up1n9TzobLkAamw8f
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/keycloak"   
            
logging.level:
  org.springframework.cloud.gateway: DEBUG
  org.springframework.security: DEBUG
  org.springframework.web.reactive.function.client: TRACE
          
```
-----

## Configure and Run Keycloak

Script will run Keycloak Docker container and create Client Scope `TEST` along
with Clients: `spring-with-test-scope` and `spring-without-test-scope`

```shell
./scripts/run-keycloak.sh
```

### The automation magic

```shell
# removed for brevity
docker exec -it keycloak /opt/jboss/keycloak/bin/kcadm.sh config credentials --server http://localhost:8080/auth --realm master --user spring --password spring123 && \
docker exec -it keycloak /opt/jboss/keycloak/bin/kcadm.sh create -x "client-scopes" -r master -s name=TEST -s protocol=openid-connect && \
docker exec -it keycloak /opt/jboss/keycloak/bin/kcadm.sh create clients -r master -s clientId=spring-without-test-scope -s enabled=true -s clientAuthenticatorType=client-secret -s secret=f6fc369d-49ce-4132-8282-5b5d413eba23 -s 'redirectUris=["*"]' && \
docker exec -it keycloak /opt/jboss/keycloak/bin/kcadm.sh create clients -r master -s clientId=spring-with-test-scope -s enabled=true -s clientAuthenticatorType=client-secret -s secret=c6480137-1526-4c3e-aed3-295aabcb7609  -s 'redirectUris=["*"]' -s 'defaultClientScopes=["TEST", "web-origins", "profile", "roles", "email"]'
# removed for brevity
```
