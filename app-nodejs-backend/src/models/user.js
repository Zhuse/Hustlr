const mongoose = require('mongoose')
const Schema = mongoose.Schema

const User = new Schema ({
    properties: { 
        score: {type: String, required: false, default: () => {return 0}},
        email: {type: String, required: true},
        name: {type: String, required: true},
        preferredCategories: {type: [String], required: true, enum: ["lifting", "homework", "transportation", "other"], default: () => {return "other"}}
    },
    additionalProperties: {
        userDescription: {type: String, required: false},
        dob: {type: Date, required: false},
        phoneNumber: {type: String, required: false}
    }
})

module.exports = mongoose.model('User', User)