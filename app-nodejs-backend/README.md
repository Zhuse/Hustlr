# API Documentation
API Documentation for our backend

User
====

__GET /user/{id}__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "score": Number,
        "email": String,
        "firstName": String,
        "lastName": String
    },
    "additionalProperties": {
        "userDescription": String,
        "dob": Date,
        "phoneNumber": String
    }
}
```

__POST /user__
Request:
```javascript
{
    "properties": {
        "email": String,
        "firstName": String,
        "lastName": String
    },
    "additionalProperties": {
        "userDescription": String,
        "dob": Date,
        "phoneNumber": String
    }
    
}
```

Response (201):
```javascript
{
    "userId": String,
    "properties": {
        "score": Number
        "email": String,
        "firstName": String,
        "lastName": String
    },
    "additionalProperties": {
        "userDescription": String
        "dob": Date
    }
}
```

Hustle
====

__GET /hustle/users/{userId}/__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "hustles": [
            {
                "hustleId": String,
                "hustlrId": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, // completed, in_prog, cancelled, posted
                "description": String
            }
        ]
    }
}
```

__GET /hustle/users/{userId}/matched__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "hustles": [
            {
                "hustleId": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, // posted
                "description": String
            }
        ]
    }
}
```

__POST /hustle/users/{userId}__
Request:
```javascript
{
    "properties": {
        "hustle": {
            "providerId": String,
            "category": String,
            "price": Number,
            "description": String
        }
    }
}
```

Response (201):
```javascript
{
    "userId": String
    "properties": {
        "hustle": {
                "hustleId": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, //posted
                "description": String
            }
        }
    }
}
```

Chat
====

__GET /chat/users/{userId}/__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "recipients": [
            {
                "recipientId": String,
                "messages": [
                    {
                        "text": String,
                        "timestamp": Date
                    }
                ]
            }
        ]
    }
}
```

__GET /chat/users/{userId}/messages/?recipientId={recipientId}__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "recipientId": String,
        "messages": [
            {
                "text": String,
                "timestamp": Date
            }
        ]
    }
}
```

__POST /chat/users/{userId}/message__
Request:
```javascript
{
    "recipientId": String,
    "properties": {
        "message" : {
            "text": String
        }
    }
}
```

Response (204):
```javascript
{}
```
