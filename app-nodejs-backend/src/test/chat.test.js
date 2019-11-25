const io = require("socket.io-client");
// const index = require('../index');

describe("Chat test", () => {
    let socket;

    beforeEach((done) => {
        socket = io.connect("http://localhost:3000", {
            "reconnection delay": 0,
            "reopen delay": 0,
            "force new connection": true
        });
        socket.on("connect", function () {
            done();
        });
        socket.on("disconnect", function () {
        });
    });

    afterEach((done) => {
        if (socket.connected) {
            socket.disconnect();
        }
        done();
    });

    test("chat can send and receive messages", (done) => {
        // once connected, emit Hello World
        socket.emit("chat message", "Hello World");

        socket.once("chat message", (message) => {
            // Check that the message matches
            expect(message).toBe("Hello World");
            done();
        });

        socket.on("connection", (socket) => {
            expect(socket).toBeDefined();
        });
    });
});
