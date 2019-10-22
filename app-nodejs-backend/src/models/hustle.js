const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Hustle = new Schema ({
    hustlrId: {type: String, required: false},
    providerId: {type: String, required: true},
    category: {type: String, required: true, enum: ["lifting", "homework", "transporation", "other"]},
    price: {type: Number, required: true},
    status: {type: String, required: false, enum: ["completed", "in_prog", "cancelled", "posted"], default: () => {return "posted"}},
    description: {type: String, required: false}
})

module.exports = mongoose.model('Hustle', Hustle)