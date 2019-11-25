const Hustle = require("../models/hustle");
const Bid = require("../models/bid");
const matchService = require("../services/matchService");
const _ = require("lodash");

exports.create = function (req, res) {
    const preInsert = _.cloneDeep(req.body.properties.hustle);
    preInsert.providerId = req.params.userId;

    const newHustle = new Hustle(preInsert);
    newHustle.save()
        .then((result) => {
            const preSend = {
                userId: req.params.userId,
                properties: {
                    hustle: result
                }
            };
            return res.status(201).send(preSend);
        })
        .catch((err) => {
            return res.status(400).send(err);
        });
};

exports.findById = function (req, res) {
    Hustle.find({ $or: [{ providerId: req.params.userId }, { hustlrid: req.params.userId }] }).populate("bids")
        .then((result) => {
            const preSend = {
                userId: req.params.userId,
                properties: {
                    hustle: result
                }
            };
            return res.status(200).send(preSend);
        })
        .catch((err) => {
            return res.status(400).send(err);
        });
};

exports.findMatches = function (req, res) {
    const matches = matchService.findMatches(req.params.userId);

    matches.then((data) => {
        return res.status(200).send(data);
    }, (err) => {
        return res.status(500).send(err);
    });
};

exports.update = function (req, res) {
    Hustle.findOneAndUpdate({ providerId: req.params.userId, _id: req.params.hustleId }, { $set: req.body.properties.hustle }, { runValidators: true, new: true })
        .populate("bids")
        .then((result) => {
            if (!result) {
                return res.status(400).send({ message: "Hustle or User does not exist." });
            }
            const preSend = {
                userId: req.params.userId,
                properties: {
                    hustle: result
                }
            };
            return res.status(200).send(preSend);
        })
        .catch((err) => {
            return res.status(400).send(err);
        });
};

exports.bid = function (req, res) {
    const preInsert = _.cloneDeep(req.body.properties);
    preInsert.userId = req.params.userId;
    preInsert.timestamp = new Date();
    const newBid = new Bid(preInsert);
    newBid.save()
        .then((result) => {
            const bidId = result._id;
            return Hustle.findOneAndUpdate({ _id: req.params.hustleId }, { $push: { bids: bidId } }, { runValidators: true, new: true }).populate("bids");
        })
        .then((newHustle) => {
            const preSend = {
                userId: req.params.userId,
                properties: {
                    hustle: newHustle
                }
            };
            return res.status(200).send(preSend);
        })
        .catch((err) => {
            return res.status(400).send(err);
        });
};
