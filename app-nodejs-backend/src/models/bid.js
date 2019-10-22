const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Bid = new Schema ({
    userId: {type: String, required: true},
    description: {type: String, required: false},
    bidCost: {type: Number, required: true},
    timestamp: {type: Date, required: true}
})

module.exports = mongoose.model('Bid', Bid)