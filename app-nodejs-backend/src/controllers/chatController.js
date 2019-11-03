const Chat = require("../models/chat");
const Message = require("../models/message");
const _ = require("lodash");
exports.create = function (req, res) {
    // Setup transaction code
    // Need to allow for potentially multiple messages to be sent
    /* const session = await mongoose.startSession()
    session.startTransaction()
    try {
        let newChat = new Chat(preInsert)
        newChat.save()
        .then(result => {
            return res.status(201).send(result)
        })
        .catch(err => {
            console.log(err)
            return res.status(400).send(err)
        })
    } catch (err) {

    } finally {

    } */
    const messagePreInsert = {};
    let messageId = "";
    const userId = req.params.userId;
    const recipientId = req.body.recipientId;
    messagePreInsert.timestamp = new Date();
    messagePreInsert.senderId = userId;
    messagePreInsert.recipientId = recipientId;
    const newMessage = new Message(messagePreInsert);
    newMessage.save()
        .then((result) => {
            messageId = result._id;
            return Chat.findOne({
                $or: [{ userOne: userId, userTwo: recipientId }, { userOne: recipientId, userTwo: userId }]
            });
        })
        .then((findResult) => {
            if (_.isEmpty(findResult)) {
                const preInsert = {
                    userOne: userId,
                    userTwo: recipientId,
                    messages: [messageId]
                };
                const newChat = new Chat(preInsert);
                return newChat.save();
            } else {
                return Chat.findOneAndUpdate({ _id: findResult._id }, { $push: { messages: messageId } });
            }
        })
        .then((result) => {
            return res.status(201).send(result);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};

exports.findRecipients = function (req, res) {
    Chat.find({ $or: [{ userOne: req.params.userId }, { userTwo: req.params.userId }] })
        .populate("messages")
        .then((result) => {
            const preSend = {
                userId: req.params.userId,
                properties: {
                    recipients: result.map((chat) => {
                        let recipientId = "";
                        if (chat.userOne === req.params.userId) {
                            recipientId = chat.userTwo;
                        } else {
                            recipientId = chat.userOne;
                        }
                        return {
                            socketId: chat._id,
                            recipientId: recipientId,
                            messages: chat.messages
                        };
                    })
                }
            };
            return res.status(200).send(preSend);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};

exports.findByIds = function (req, res) {
    Chat.findOne({ $or: [{ userOne: req.params.userId, userTwo: req.query.recipientId }, { userOne: req.query.recipientId, userTwo: req.params.userId }] })
        .populate("messages")
        .then((result) => {
            const preSend = {
                userId: req.params.userId,
                properties: {
                    socketId: result._id,
                    recipientId: req.query.recipientId,
                    messages: result.messages
                }
            };
            return res.status(200).send(preSend);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};
