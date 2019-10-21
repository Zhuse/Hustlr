const mongoose = require('mongoose');

const MONGO_USERNAME = 'cpen321';
const MONGO_PASSWORD = 'password';
const MONGO_HOSTNAME = 'mongo';//'127.0.0.1';
const MONGO_PORT = '27017';
const MONGO_DB = 'hustlr';

const url = `mongodb://${MONGO_USERNAME}:${MONGO_PASSWORD}@${MONGO_HOSTNAME}:${MONGO_PORT}/${MONGO_DB}?authSource=admin`;

mongoose.connect(url, {useNewUrlParser: true, useUnifiedTopology: true});