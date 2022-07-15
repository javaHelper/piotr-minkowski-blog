# Config Server with Microservices

GET -> `localhost:9999/department/organization/1/with-employees`

Response:

```
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

GET -> `http://localhost:7777/organization/1/with-departments`

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

GET -> `http://localhost:7777/organization/1/with-departments-and-employees`

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

GET -> `localhost:8012/config-service/properties`

Response:

```json
{
    "name": "config-service",
    "profiles": [
        "properties"
    ],
    "label": null,
    "version": "274012187570cbfb95dd0312861f0d3c64a32f00",
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


