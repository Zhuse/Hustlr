const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Hustle = new Schema ({
    hustlrId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    },
    providerId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    },
    category: {type: String, required: true, enum: ["lifting", "homework", "transportation", "other"]},
    price: {type: Number, required: true},
    status: {type: String, required: false, enum: ["completed", "in_prog", "cancelled", "posted"], default: () => {return "posted"}},
    description: {type: String, required: false},
    title: {type: String, required: true},
    location: {type: String, required: false},
    bids: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Bid'
    }]
})

module.exports = mongoose.model('Hustle', Hustle)