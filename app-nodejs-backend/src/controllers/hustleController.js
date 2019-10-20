const Hustle = require('../models/hustle');
const _ = require('lodash');

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
        console.log(err);
        return res.status(400).send(err)
    })
}