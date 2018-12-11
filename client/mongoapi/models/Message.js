const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let Message = new Schema({
  email: {
    type: String
  },
  message: {
    type: String
  }
},{
  collection: 'message'
});

module.exports = mongoose.model('Message', Message);
