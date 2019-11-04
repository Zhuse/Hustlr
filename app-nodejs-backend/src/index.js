const cors = require("cors");
const bodyParser = require("body-parser");
const express = require("express");

const routes = require("./routes");

/* eslint-disable-next-line */
const db = require("./db");

const services = require("./services");
const app = express();

// Application-Level Middleware

app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use((err, req, res, next) => {
    // body-parser will set this to 400 if the json is in error
    if (err.status === 400) { return res.status(err.status).send({ message: "Invalid JSON." }); }

    return res.status(500).send({ message: "Something went wrong." });
});
// Routes

app.use("/hustle", routes.hustle);
app.use("/user", routes.user);
app.use("/chat", routes.chat);

// Start

const server = app.listen(3000, () => {});

// bootup socketio

const socketio = require("socket.io")(server);
services.chatService.startSockets(socketio);
