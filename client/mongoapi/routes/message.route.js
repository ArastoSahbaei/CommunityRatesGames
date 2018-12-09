const express = require('express');
const app = express();
const messageRoutes = express.Router();

let Message = require('../model/Message');

messageRoutes.route('/add').post(function (req, res) {
  let message = new Message(req.body);
  message.save()
    .then(contact => {
      res.status(200).json({'message': 'New message was received!'});
    })
    .catch(error => {
      res.status(400).send("Something went wrong. Please try again");
    });
});

module.exports = messageRoutes;
