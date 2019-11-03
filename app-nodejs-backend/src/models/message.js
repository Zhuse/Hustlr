const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const Message = new Schema({
    senderId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User"
    },
    recipientId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User"
    },
    text: { type: String, required: true },
    timestamp: { type: Date, required: true }
});

module.exports = mongoose.model("Message", Message);
