//****
var request = require('superagent');
// extend with Request#proxy() 
require('superagent-proxy')(request);
//****
var express = require("express"); 
var app = express(); 
//****
var access_token = 'put here your access token';
var proxy_host = "your proxy";
var proxy_port = 'your proxy port';
proxy = proxy_host+':'+proxy_port;
//****
var url = 'https://api.spotify.com/v1/artists?ids=';
var id_artists = '2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6';
//****
request
  .get(url + id_artists)
  .proxy(proxy)
  .set('Content-Type', 'application/json')
  .set('Authorization', 'Bearer ' + access_token)
  .end(onResponse);
//****  
function onResponse (err, res) {
  if (err) {
    console.log(err);
  } else {
    console.log(res.status, res.headers);
    console.log(res.body);
    giveMeInfoAboutArtists(res.body);
  }
  
}
//****
function giveMeInfoAboutArtists(info){
    app.get('/', function(req, res) { 
        res.send('Accede a /artists para obtener información de los artistas'); 
    }); 
    app.get('/artists', function(req, res) { 
        res.send(info); 
    });
    app.listen(8080); 
}




 



