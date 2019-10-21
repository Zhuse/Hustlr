const app = require('../index.js');
const io = app.socketio;

io.sockets.on('connection', function(socket) {
    console.log("new socket connection");

    socket.on('chat message', function(message) {
        console.log("new chat message: " + message);
        io.emit('chat message',  socket.username + ' ' + message);
    });

});
