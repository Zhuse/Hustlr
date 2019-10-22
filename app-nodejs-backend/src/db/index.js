const mongoose = require('mongoose');

const MONGO_USERNAME = 'cpen321';
const MONGO_PASSWORD = 'b2ygRqSgVJaKLkWpqNLrG5rM';
const MONGO_HOSTNAME = 'cpen321-hustlr-pytyl.gcp.mongodb.net';
const MONGO_DB = 'hustlr';

const url = `mongodb+srv://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOSTNAME}/${MONGO_DB}?retryWrites=true&w=majority`;

mongoose.connect(url, {useNewUrlParser: true, useUnifiedTopology: true});
