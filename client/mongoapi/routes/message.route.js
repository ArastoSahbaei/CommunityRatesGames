const express = require('express');
const app = express();
const messageRoutes = express.Router();

let Message = require('../models/Message');

messageRoutes.route('/add').post(function (req, res) {
  let message = new Message(req.body);
  console.log(req.body);
  message.save()
    .then(message => {
      res.status(200).json({'message': 'message in added successfully'});
    })
    .catch(err => {
      res.status(403).send("unable to save to database");
    });
});

module.exports = messageRoutes;
