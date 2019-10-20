const cors = require('cors');
const bodyParser = require('body-parser');
const express = require('express');

const routes = require('./routes');
const db = require('./db');
const app = express();

// Application-Level Middleware

app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
// Routes

app.use('/hustle', routes.hustle);
app.use('/user', routes.user);
app.use('/chat', routes.chat);

// Start

app.listen(3000, () =>
  console.log(`Example app listening on port ${3000}!`),
);
