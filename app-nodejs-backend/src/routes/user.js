const { Router } = require('express')
const userController = require('../controllers/userController')

const router = Router()

router.get('/:userId', (req, res) => {
  userController.findById(req, res)
})

router.post('/', (req, res) => {
  userController.create(req, res)
})

module.exports = router
