# starter-kit
This java starter-kit is not only write by me, credit is on each file.

## example

API example

### ping

```
GET http://localhost:8080/ping
```

Response

```
{
    "error": null,
    "data": "December 23, 2019 1:39:52 PM WIB",
    "isSuccess": true
}
```

### message

```
POST http://localhost:8080/message
```
Request

```
{
	"message" : "message 1",
	"createdBy": "aa"
}
```

Response
```
{
    "error": null,
    "data": {
        "id": "119a09b9-0648-40a4-9841-589e4b9febe9",
        "createdBy": "aa",
        "createdAt": 1577083480184,
        "updatedAt": 1577083480238,
        "isActive": true,
        "message": "message 1"
    },
    "isSuccess": true
}
```


```
GET http://localhost:8080/message?page=0&limit=10
```

Response
```
{
    "error": null,
    "data": [
        {
            "id": "119a09b9-0648-40a4-9841-589e4b9febe9",
            "createdBy": "aa",
            "createdAt": 1577083480184,
            "updatedAt": 1577083480238,
            "isActive": true,
            "message": "message 1"
        }
    ],
    "pages": "1",
    "elements": "1",
    "isSuccess": true
}
```

```
GET http://localhost:8080/message/{id}
```
Response
```
{
    "error": null,
    "data": {
        "id": "119a09b9-0648-40a4-9841-589e4b9febe9",
        "createdBy": "aa",
        "createdAt": 1577083480184,
        "updatedAt": 1577083480238,
        "isActive": true,
        "message": "message 1"
    },
    "isSuccess": true
}
```


