const express = require('express'),
  bodyParser = require('body-parser'),
  cors = require('cors'),
  mongoose = require('mongoose'),
  config = require('./src/mongo/DB');

  const app = express();

  mongoose.Promise = global.Promise;

  mongoose.connect(config.DB).then(
    () => {console.log('Connection to database') },
    error => { console.log('Can not find a database' + error)}
  );

  const messageRoute = require('./src/app/shared/routes/message.route');

  app.use(bodyParser.json);
  app.use(cors());

  const port = process.env.PORT || 4000;

  app.use('/messages', messageRoute);

  const server = app.listen(port, function () {
    console.log('Listening on port: ' + port);
  });
