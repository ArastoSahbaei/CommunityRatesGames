const express = require('express'),
  path = require('path'),
  bodyParser = require('body-parser'),
  cors = require('cors'),
  mongoose = require('mongoose'),
  config = require('./DB');

  const messageRoute = require('./routes/message.route');

  mongoose.Promise = global.Promise;

  mongoose.connect(config.DB, { useNewUrlParser : true }).then(
    () => {console.log('Connection to database') },
    error => { console.log('Can not find a database' + error)}
  );

  const app = express();
  app.use(bodyParser.json);
  app.use(cors());
  app.use('/messages', messageRoute);

  const port = process.env.PORT || 4000;

  const server = app.listen(port, function () {
    console.log('Listening on port: ' + port);
  });
