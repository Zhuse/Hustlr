const User = require("../models/user");
const jwtDecode = require("jwt-decode");

exports.create = function (req, res) {
    const newUser = new User(req.body);
    console.log(req.body);
    newUser.save()
        .then((result) => {
            return res.status(200).send(result);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};

exports.findById = function (req, res) {
    User.findById(req.params.userId)
        .then((result) => {
            if (!result) {
                return res.status(400).send({ message: "User does not exist." });
            }
            return res.status(201).send(result);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};

exports.findByToken = function (req, res) {
    const decoded = jwtDecode(req.header("IdToken"));
    User.findOne({ "properties.email": decoded.email })
        .then((result) => {
            if (!result) {
                return res.status(400).send({ message: "User does not exist." });
            }
            return res.status(200).send(result);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};

exports.update = function (req, res) {
    User.findOneAndUpdate({ _id: req.params.userId }, { $set: req.body }, { runValidators: true, new: true })
        .then((result) => {
            if (!result) {
                return res.status(400).send({ message: "User does not exist." });
            }
            return res.status(200).send(result);
        })
        .catch((err) => {
            console.log(err);
            return res.status(400).send(err);
        });
};
