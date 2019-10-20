const { Router } = require('express')
const hustlr = require('../controllers/hustleController')
const router = Router()

router.get('/users/:userId', (req, res) => {
  hustlr.findById(req, res)
})

router.get('/users/:userId/matched', (req, res) => {
  hustlr.findById(req, res)
})

router.post('/users/:userId', (req, res) => {
  hustlr.create(req, res)
})

module.exports = router
