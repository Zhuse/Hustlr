const express = require("express");
const userController = require("../controllers/userController");

const router = express.Router();

router.get("/signOn", (req, res) => {
    userController.findByToken(req, res);
});

router.get("/:userId", (req, res) => {
    userController.findById(req, res);
});

router.patch("/:userId", (req, res) => {
    userController.findById(req, res);
});

router.post("/", (req, res) => {
    userController.create(req, res);
});

module.exports = router;
