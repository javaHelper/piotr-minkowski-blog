# Spring Cloud APIGW and Microservices and OpenAPI Specification

# Sequence to start Microservices

- config-server
- discovery-server
- employee-service
- department-service
- organization-service
- gateway-service

![piomin](https://user-images.githubusercontent.com/54174687/179191314-df19702b-24d9-4f70-84d5-19b417594173.png)


GET -> `localhost:8011/department/organization/1/with-employees`

Response:

```json
[
    {
        "id": 1,
        "organizationId": 1,
        "name": "Development",
        "employees": [
            {
                "id": 1,
                "name": "John Smith",
                "age": 34,
                "position": "Analyst"
            },
            {
                "id": 2,
                "name": "Darren Hamilton",
                "age": 37,
                "position": "Manager"
            },
            {
                "id": 3,
                "name": "Tom Scott",
                "age": 26,
                "position": "Developer"
            }
        ]
    },
    {
        "id": 2,
        "organizationId": 1,
        "name": "Operations",
        "employees": [
            {
                "id": 1,
                "name": "John Smith",
                "age": 34,
                "position": "Analyst"
            },
            {
                "id": 2,
                "name": "Darren Hamilton",
                "age": 37,
                "position": "Manager"
            },
            {
                "id": 3,
                "name": "Tom Scott",
                "age": 26,
                "position": "Developer"
            }
        ]
    }
]
```
---------

GET -> `http://localhost:8011/organization/1/with-departments`

Response:

```json
{
    "id": 1,
    "name": "Microsoft",
    "address": "Redmond, Washington, USA",
    "departments": [
        {
            "id": 1,
            "name": "Development"
        },
        {
            "id": 2,
            "name": "Operations"
        }
    ]
}
```
---------

GET -> `http://localhost:8011/organization/1/with-departments-and-employees`

Response:

```json
{
    "id": 1,
    "name": "Microsoft",
    "address": "Redmond, Washington, USA",
    "departments": [
        {
            "id": 1,
            "name": "Development",
            "employees": [
                {
                    "id": 1,
                    "name": "John Smith",
                    "age": 34,
                    "position": "Analyst"
                },
                {
                    "id": 2,
                    "name": "Darren Hamilton",
                    "age": 37,
                    "position": "Manager"
                },
                {
                    "id": 3,
                    "name": "Tom Scott",
                    "age": 26,
                    "position": "Developer"
                }
            ]
        },
        {
            "id": 2,
            "name": "Operations",
            "employees": [
                {
                    "id": 1,
                    "name": "John Smith",
                    "age": 34,
                    "position": "Analyst"
                },
                {
                    "id": 2,
                    "name": "Darren Hamilton",
                    "age": 37,
                    "position": "Manager"
                },
                {
                    "id": 3,
                    "name": "Tom Scott",
                    "age": 26,
                    "position": "Developer"
                }
            ]
        }
    ]
}
```

GET -> `localhost:8011/employee/`

Response:

```json
[
    {
        "id": 1,
        "organizationId": 1,
        "departmentId": 1,
        "name": "John Smith",
        "age": 34,
        "position": "Analyst"
    },
    {
        "id": 2,
        "organizationId": 1,
        "departmentId": 1,
        "name": "Darren Hamilton",
        "age": 37,
        "position": "Manager"
    },
    {
        "id": 3,
        "organizationId": 1,
        "departmentId": 1,
        "name": "Tom Scott",
        "age": 26,
        "position": "Developer"
    },
    {
        "id": 4,
        "organizationId": 1,
        "departmentId": 2,
        "name": "Anna London",
        "age": 39,
        "position": "Analyst"
    },
    {
        "id": 5,
        "organizationId": 1,
        "departmentId": 2,
        "name": "Patrick Dempsey",
        "age": 27,
        "position": "Developer"
    },
    {
        "id": 6,
        "organizationId": 2,
        "departmentId": 3,
        "name": "Kevin Price",
        "age": 38,
        "position": "Developer"
    },
    {
        "id": 7,
        "organizationId": 2,
        "departmentId": 3,
        "name": "Ian Scott",
        "age": 34,
        "position": "Developer"
    },
    {
        "id": 8,
        "organizationId": 2,
        "departmentId": 3,
        "name": "Andrew Campton",
        "age": 30,
        "position": "Manager"
    },
    {
        "id": 9,
        "organizationId": 2,
        "departmentId": 4,
        "name": "Steve Franklin",
        "age": 25,
        "position": "Developer"
    },
    {
        "id": 10,
        "organizationId": 2,
        "departmentId": 4,
        "name": "Elisabeth Smith",
        "age": 30,
        "position": "Developer"
    }
]
```

-----

GET -> `localhost:8012/config-service/properties`

Response:

```json
{
    "name": "config-service",
    "profiles": [
        "properties"
    ],
    "label": null,
    "version": "a4059947cac83ba16b5579b750c59ccea8e4980b",
    "state": null,
    "propertySources": [
        {
            "name": "https://github.com/javaHelper/piotr-minkowski-blog/3.sample-spring-microservices-new/sample-spring-microservices-config-piotr/application.properties",
            "source": {
                "eureka.client.serviceUrl.defaultZone": "http://localhost:8761/eureka",
                "eureka.instance.instance-id": "${spring.application.name}:${spring.application.instance_id:${random.value}}",
                "spring.datasource.url": "jdbc:mysql://localhost:3306/piomin_app?createDatabaseIfNotExist=true&serverTimezone=UTC",
                "spring.datasource.username": "root",
                "spring.datasource.password": "Password",
                "spring.jpa.hibernate.ddl-auto": "update",
                "spring.jpa.properties.hibernate.show_sql": "true",
                "spring.jpa.properties.hibernate.use_sql_comments": "true",
                "spring.jpa.properties.hibernate.format_sql": "true"
            }
        }
    ]
}
```

