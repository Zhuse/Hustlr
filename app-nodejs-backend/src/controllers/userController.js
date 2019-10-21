const User = require('../models/user')

exports.create = function (req, res) {
    let newUser = new User(req.body)
    console.log(req.body)
    newUser.save()
    .then(result => {
        return res.status(200).send(result)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}

exports.findById = function (req, res) {
    User.findById(req.params.userId)
    .then(result => {
        if (!result) {
            return res.status(400).send(err)
        }
        return res.status(201).send(result)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}

exports.findByEmail = function (req, res) {
    User.findOne({"properties.email": req.params.email})
    .then(result => {
        if (!result) {
            return res.status(400).send(err)
        }
        return res.status(200).send(result)
    })
    .catch(err => {
        console.log(err)
        return res.status(400).send(err)
    })
}