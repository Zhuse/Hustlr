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
    "userId": String,
    "properties": {
        "email": String,
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
        "email": 
    },
    "additionalProperties": {
        "userDescription": String
        "dob": Date
    }
}
```

Hustle
====

__GET /hustle/{userId}__
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

__GET /hustle/{userId}/matched__
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

__POST /hustle/{userId}__
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
        "hustles": [
            {
                "hustleId": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, //posted
                "description": String
            }
        ]
    }
}
```

Chat
====

__GET /chat/{userId}__
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

__GET /chat/{userId}/{recipientId}__
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

__POST /chat/{userId}__
Request:
```javascript
{
    "recipientId": String,
    "properties": {
        "message" : {
            "text": String,
            "timestamp": Date
        }
    }
}
```

Response (201):
```javascript
{}
```
