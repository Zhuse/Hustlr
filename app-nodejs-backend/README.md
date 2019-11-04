# API Documentation
API Documentation for our backend

## User
====

__GET /user/{id}__
Response (200):
```javascript
{
    "_id": String
    "properties": {
        "score": Number,
        "email": String,
        "name": String
    },
    "additionalProperties": {
        "userDescription": String,
        "dob": Date,
        "phoneNumber": String
    }
}
```

__GET /user/signOn__ with IdToken in header
Response (200):
```javascript
{
    "_id": String
    "properties": {
        "score": Number,
        "email": String,
        "name": String
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
        "name": String
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
    "_id": String,
    "properties": {
        "score": Number
        "email": String,
        "name": String
    },
    "additionalProperties": {
        "userDescription": String
        "dob": Date
    }
}
```

## Hustle
====

__GET /hustle/users/{userId}/__
Response (200):
```javascript
{
    "userId": String
    "properties": {
        "hustles": [
            {
                "_id": String,
                "hustlrId": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, // completed, in_prog, cancelled, posted
                "description": String,
                "title": String,
                "location": String
                "bids": [{
                    "userId": String,
                    "description": String,
                    "bidCost": Number,
                    "timestamp": Date
                }]
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
                "_id": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, // posted
                "description": String,
                "title": String,
                "location": String,
                "bids": [{
                    "userId": String,
                    "description": String,
                    "bidCost": Number,
                    "timestamp": Date
                }]
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
            "title": String,
            "location": String
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
                "_id": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, //posted
                "description": String,
                "title": String,
                "location": String,
                "bids": []
            }
        }
    }
}
```

__POST /hustle/users/{userId}/{hustleId}/bid__
Request:
```javascript
{
    "properties": {
        "description": String,
        "bidCost": Number
    }
}
```

Response (201):
```javascript
{
    "userId": String
    "properties": {
        "hustle": {
                "_id": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, //posted
                "description": String,
                "title": String,
                "location": String,
                "bids": [{
                    "userId": String,
                    "description": String,
                    "bidCost": Number,
                    "timestamp": Date
                }]
            }
        }
    }
}
```

__PATCH /hustle/users/{userId}/{hustleId}__
Request:
```javascript
{
    "properties": {
        "hustle": {
            // Whatever you want to change here
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
                "_id": String,
                "providerId": String,
                "category": String,
                "price": Number,
                "status": String, // completed, in_prog, posted, cancelled
                "description": String,
                "title": String,
                "location": String,
                "bids": [{
                    "userId": String,
                    "description": String,
                    "bidCost": Number,
                    "timestamp": Date
                }]
            }
        }
    }
}
```

## Chat
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
                        "timestamp": Date,
                        "senderId": String,
                        "recipientId": String
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
                "timestamp": Date,
                "senderId": String,
                "recipientId": String
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
