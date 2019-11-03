const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const Chat = new Schema({
    userOne: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User"
    },
    userTwo: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User"
    },
    messages: [
        {
            type: mongoose.Schema.Types.ObjectId,
            ref: "Message"
        }
    ]
});

module.exports = mongoose.model("Chat", Chat);
