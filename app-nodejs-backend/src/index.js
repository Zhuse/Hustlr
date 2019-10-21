const cors = require('cors')
const bodyParser = require('body-parser')
const express = require('express')

const routes = require('./routes')
const db = require('./db')
const app = express()



// Application-Level Middleware

app.use(cors())

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))
app.use((err, req, res, next) => {
  console.error(err); 

  // body-parser will set this to 400 if the json is in error
  if(err.status === 400)
    return res.status(err.status).send({ message: 'Invalid JSON.'});

  return res.status(500).send({ message: 'Something went wrong.'});
});
// Routes

app.use('/hustle', routes.hustle)
app.use('/user', routes.user)
app.use('/chat', routes.chat)

// Start

const server = app.listen(3000, () =>
  console.log(`Hustlr server running on port ${3000}`),
)





// var http = require('http').createServer(app);
const io = require('socket.io')(server);

// sockets

io.sockets.on('connection', function(socket) {
  console.log("new socket connection");

  socket.on('chat message', function(message) {
    console.log("new chat message: " + message);
    io.emit('chat message',  socket.username + ' ' + message);
  });

});








