// const io = require('socket.io')(http);
//
// io.sockets.on('connection', function(socket) {
//     // socket.on('username', function(username) {
//     //     socket.username = username;
//     //     io.emit('is_online', '🔵 <i>' + socket.username + ' join the chat..</i>');
//     // });
//
//     // socket.on('disconnect', function(username) {
//     //     io.emit('is_online', '🔴 <i>' + socket.username + ' left the chat..</i>');
//     // })
//
//     socket.on('new message', function(message) {
//         io.emit('chat_message', socket.username + ' ' + message);
//     });
//
// });