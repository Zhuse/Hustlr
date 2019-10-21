// init script for docker compose mongo
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

db.createCollection("users");
db.createCollection("hustles");
db.createCollection("messages");
db.createCollection("chats");
