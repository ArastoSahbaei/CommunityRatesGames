const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let Message = new Schema({
  person_name: {
    type: String
  },
  business_name: {
    type: String
  }
},{
  collection: 'message'
});

module.exports = mongoose.model('Message', Message);
