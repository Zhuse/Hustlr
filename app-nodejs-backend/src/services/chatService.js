module.exports.startSockets = function(io) {
    io.sockets.on('connection', function(socket) {
        console.log("new socket connection");

        socket.on('chat message', function(message) {
            console.log("new chat message: " + message);
            io.emit('chat message', message);
        });
    });
};
