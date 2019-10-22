const Hustle = require('../models/hustle')
const User = require('../models/user')
const Bid = require('../models/bid')
const _ = require('lodash')

exports.create = function (req, res) {
    let preInsert = _.cloneDeep(req.body.properties.hustle)
    preInsert.providerId = req.params.userId

    let newHustle = new Hustle(preInsert)
    newHustle.save()
    .then(result => {
        let preSend = {
            userId: req.params.userId,
            properties: {
                hustle: result
            }
        }
        return res.status(201).send(preSend)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}

exports.findById = function (req, res) {
    Hustle.find({$or: [{providerId: req.params.userId}, {hustlrid: req.params.userId}]}).populate('bids')
    .then(result => {
        let preSend = {
            userId: req.params.userId,
            properties: {
                hustle: result
            }
        }
        return res.status(200).send(preSend)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}

// TODO break logic out
exports.findMatches = function (req, res) {
    User.findById(req.params.userId)
    .then(result => {
        // TODO err case
        let currUser = result;
        let userScore = currUser.properties.score;
        let preferredCategories = currUser.properties.preferredCategories;

        Hustle.find({status: "posted", providerId: {$ne: req.params.userId}}).populate('bids')
        .then(results => {

            let providerIds = results.map((result) => {return result.providerId});

            User.find({_id: {$in: providerIds}})
            .then( providers => {
                let validProviders = providers.filter((provider) => {
                    return Math.abs(provider.properties.score - userScore) < 10;
                });

                let validProviderIds = validProviders.map((provider) => {
                    return provider._id.toString();
                });

                let filtered = [];

                results.forEach((hustle) => {
                    if (validProviderIds.includes(hustle.providerId.toString()) &&
                            preferredCategories.includes(hustle.category)) {
                        filtered.push(hustle);
                    }
                });

                let preSend = {
                    userId: req.params.userId,
                    properties: {
                        hustles: filtered
                    }
                };
                return res.status(200).send(preSend)

            })
            .catch(err => {
                console.log(err)
                return res.status(500).send(err)

            });

        })
        .catch(err => {
            console.log(err)
            return res.status(400).send(err)
        });
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })

}

exports.update = function (req, res) {
    Hustle.findOneAndUpdate({providerId: req.params.userId, _id: req.params.hustleId}, { $set: req.body.properties.hustle }, {runValidators: true, new: true})
    .then(result => {
        if (!result) {
            return res.status(400).send({message: "Hustle or User does not exist."})
        }
        let preSend = {
            userId: req.params.userId,
            properties: {
                hustle: result
            }
        }
        return res.status(200).send(preSend)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}

exports.bid = function (req, res) {
    let preInsert = _.cloneDeep(req.body.properties)
    preInsert.userId = req.params.userId
    preInsert.timestamp = new Date()
    let newBid = new Bid(preInsert)
    newBid.save()
    .then(result => {
        let bidId = result._id
        return Hustle.findOneAndUpdate({ _id: req.params.hustleId}, {$push: { bids: bidId}}, {runValidators: true, new: true}).populate('bids')
    })
    .then(newHustle => {
        let preSend = {
            userId: req.params.userId,
            properties: {
                hustle: newHustle
            }
        }
        return res.status(200).send(preSend)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}