const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const Chat = new Schema ({
    userOne: {type: String, required: true},
    userTwo: {type: String, required: true},
    messages: [
        {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Message'
        }
    ]
})

module.exports = mongoose.model('Chat', Chat)