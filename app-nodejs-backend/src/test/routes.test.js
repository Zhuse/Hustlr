const request = require("supertest");
const index = require("../index");
const app = index.app;

describe("User Endpoints", () => {
    test("can create an user with the given fields", async () => {
        const res = await request(app)
            .post("/user")
            .send({
                properties: {
                    email: "test@test.ca",
                    name: "test",
                    preferredCategories: "homework"
                }
            });
        expect(res.status).toBe(200);
        expect(res.body.properties.email).toBe("test@test.ca");
    });

    test("create returns 400 when required fields are missing", async () => {
        const res = await request(app)
            .post("/user")
            .send({
                properties: {
                    email: "missingNameField@test.ca"
                }
            });
        expect(res.status).toBe(400);
    });

    test("can find an user given a userid", async () => {
        const res = await request(app)
            .get("/user/5dae51be848936125abdcfd3");
        expect(res.status).toBe(201);
        expect(res.body.properties.email).toBe("aut@gmail.com");
    });

    test("returns 400 when userId does not exist", async () => {
        const res = await request(app)
            .get("/user/doesnotexist");
        expect(res.status).toBe(400);
    });

    test("update successfully updates an user", async () => {
        const res = await request(app)
            .patch("/user/5dae51be848936125abdcfd3")
            .send({
                properties: {
                    preferredCategories: "lifting"
                }
            });
        expect(res.status).toBe(201);
    });
});

describe("Hustle endpoints", () => {
    test("can create a hustle for a user", async (done) => {
        const res = await request(app)
            .post("/hustle/users/5dae51be848936125abdcfd3")
            .send({
                properties: {
                    hustle: {
                        category: "other",
                        price: 30,
                        description: "unit test",
                        title: "unit test",
                        location: "unit test"
                    }
                }
            });

        expect(res.status).toBe(201);
        expect(res.body.properties.hustle.providerId).toBe("5dae51be848936125abdcfd3");

        done();
    });

    test("hustle create returns 400 when required fields are missing", async () => {
        const res = await request(app)
            .post("/hustle/users/5dae51be848936125abdcfd3")
            .send({
                properties: {
                    hustle: {
                        title: "unit test"
                    }
                }
            });

        expect(res.status).toBe(400);
    });

    test("can find all the hustles belonging to a user", async () => {
        const res = await request(app)
            .get("/hustle/users/5dae8a3cc3c2cd1b380923d5");
        expect(res.status).toBe(200);
    });

    test("can find all the hustles matched to a user", async () => {
        const res = await request(app)
            .get("/hustle/users/5dae8a3cc3c2cd1b380923d5/matched");
        expect(res.status).toBe(200);
        expect(res.body.properties.hustles.length).toBe(4);
    });

    test("can update a hustle", async () => {
        const res = await request(app)
            .patch("/hustle/users/5dae51be848936125abdcfd3/5dd2c480b35a63a4d45bb041")
            .send({
                properties: {
                    hustle: {
                        title: "unit test update"
                    }
                }
            });
        expect(res.status).toBe(200);
        expect(res.body.properties.hustle.title).toBe("unit test update");
    });

    test("can bid on a hustle", async () => {
        const res = await request(app)
            .post("/hustle/users/5dae51be848936125abdcfd3/5dd2c480b35a63a4d45bb041/bid")
            .send({
                properties: {
                    description: "unit test bid",
                    bidCost: 42
                }
            });
        expect(res.status).toBe(200);
    });
});
