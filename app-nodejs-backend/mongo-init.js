// init script for docker compose mongo
/* eslint-disable */
const db = global.db
db.createUser(
    {
        user: "cpen321",
        pwd: "password",
        roles: [
            {
                role: "readWrite",
                db: "hustlr"
            }
        ]
    }
);

db.createCollection("Users");
db.createCollection("Hustles");
db.createCollection("Messages");
db.createCollection("Chats");
