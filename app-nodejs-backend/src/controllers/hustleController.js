const Hustle = require('../models/hustle')
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
    Hustle.find({providerId: req.params.userId})
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
    Hustle.find({status: "posted"})
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