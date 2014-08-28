var http =              require('http');
var express =           require('express');
var bodyParser =		require('body-parser')
var app =               express();


app.use(express.static(__dirname + '/public/'));
app.use(bodyParser());
app.set("view options", {layout: false});

var httpServer = http.createServer(app);
httpServer.listen(3000);
console.log("Listening on port 3000");

app.get("/", function(req, res){
	res.sendfile("./public/index.html");
})

var io = require('socket.io').listen(httpServer);

io.on('connection', function(socket){
	console.log("Connection established.")
	socket.emit("greeting","Hello!");
    socket.on('event', function(data){

    });
    socket.on('disconnect', function(){
    	console.log("Client disconnected");
    });
    socket.on('test', function(data){
    	console.log(data);
    	socket.emit("event","huirrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    })
});