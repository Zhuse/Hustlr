const Hustle = require('../models/hustle')
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

exports.findMatches = function (req, res) {
    Hustle.find({status: "posted", providerId: {$ne: req.params.userId}}).populate('bids')
    .then(result => {
        let preSend = {
            userId: req.params.userId,
            properties: {
                hustles: result
            }
        }
        return res.status(200).send(preSend)
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