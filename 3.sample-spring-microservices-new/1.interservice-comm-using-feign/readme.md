# Interservice communication using Feign Client

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

GET -> `localhost:9999/department/organization/1/with-employees`

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
