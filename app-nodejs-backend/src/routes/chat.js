const express = require("express");
const chat = require("../controllers/chatController");
const router = express.Router();

router.get("/users/:userId", (req, res) => {
    chat.findRecipients(req, res);
});

router.get("/users/:userId/message", (req, res) => {
    chat.findByIds(req, res);
});

router.post("/users/:userId/message", (req, res) => {
    chat.create(req, res);
});

module.exports = router;
