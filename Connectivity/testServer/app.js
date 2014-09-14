var express = require("express");
var bodyParser = require("body-parser");
var app = express();

app.set("view options", {layout: false});
app.use(bodyParser({strict: false}));

var port = Number(process.env.PORT || 5001);
app.listen(port, function(){
  console.log("Listening on "+port);

});

app.get("/", function(req, res){
  res.send("hello, friend");
})
