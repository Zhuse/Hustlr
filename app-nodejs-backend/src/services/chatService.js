module.exports.startSockets = function (io) {
    io.sockets.on("connection", function (socket) {
        socket.on("chat message", function (message) {
            io.emit("chat message", message);
        });
    });
};
