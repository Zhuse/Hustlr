const Hustle = require("../models/hustle");
const User = require("../models/user");

module.exports.findMatches = function (userId) {
    return new Promise((resolve, reject) => {
        User.findById(userId)
            .then((result) => {
                const currUser = result;
                const userScore = currUser.properties.score;
                const preferredCategories = currUser.properties.preferredCategories;

                Hustle.find({ status: "posted", providerId: { $ne: userId } })
                    .populate("bids")
                    .populate("providerId")
                    .then((results) => {
                        const validHustles = results.filter((hustle) => {
                            return preferredCategories.includes(hustle.category);
                        }).filter((hustle) => {
                            return Math.abs(hustle.providerId.properties.score - userScore) < 10;
                        }).map((hustle) => {
                            hustle.providerId = hustle.providerId._id;
                            return hustle;
                        });
                        const properties = {
                            hustles: validHustles
                        };
                        const data = {
                            userId,
                            properties
                        };
                        resolve(data);
                    })
                    .catch((err) => {
                        reject(err);
                    });
            })
            .catch((err) => {
                reject(err);
            });
    });
};
