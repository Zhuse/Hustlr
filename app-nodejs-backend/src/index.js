const cors = require('cors');
const bodyParser = require('body-parser');
const express = require('express');

const models = require('./models');
const routes = require('./routes');

const app = express();

// Application-Level Middleware

app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use((req, res, next) => {
  req.context = {
    models,
    me: models.users[1],
  };
  next();
});

// Routes

app.use('/session', routes.session);
app.use('/users', routes.user);
app.use('/messages', routes.message);

// Start

app.listen(3000, () =>
  console.log(`Example app listening on port ${3000}!`),
);
