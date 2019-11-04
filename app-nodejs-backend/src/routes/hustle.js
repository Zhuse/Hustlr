const { Router } = require("express");
const hustle = require("../controllers/hustleController");
const router = new Router();

router.get("/users/:userId", (req, res) => {
    hustle.findById(req, res);
});

router.get("/users/:userId/matched", (req, res) => {
    hustle.findMatches(req, res);
});

router.post("/users/:userId/:hustleId/bid", (req, res) => {
    hustle.bid(req, res);
});

router.post("/users/:userId", (req, res) => {
    hustle.create(req, res);
});

router.patch("/users/:userId/:hustleId", (req, res) => {
    hustle.update(req, res);
});
module.exports = router;
