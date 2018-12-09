const express = require('express');
const app = express();
const messageRoutes = express.Router();

let Messages = require('../model/message');

messageRoutes.route('/add').post(function (req, res) {
  let message = new Messages(req.body);
  console.log("inside routes");
  message.save()
    .then(contact => {
      res.status(200).json({'message': 'New message was received!'});
    })
    .catch(error => {
      res.status(400).send("Something went wrong. Please try again");
    });
});

module.exports = messageRoutes;
