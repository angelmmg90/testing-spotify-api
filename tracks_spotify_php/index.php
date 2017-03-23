<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            require 'vendor/autoload.php';  
            require 'vendor/mashape/unirest-php/src/Unirest.php';
           
            //Unirest\Request::proxy('your_ip_proxy', your_proxy_port, CURLPROXY_HTTP);
            //Unirest\Request::verifyPeer(false); // Disables SSL cert validation*/
            
            if (isset($_GET['ids'])) {
                 $response = Unirest\Request::get('https://api.spotify.com/v1/tracks?ids='.$_GET['ids'], $headers = array('Accept' => 'application/json',
                    'Authorization' => 'Bearer your_access_token'),
                    $parameters = null);

                 
                print_r(json_encode($response));
                
                echo $_GET['ids'];
            }else{
                echo "Debes poner el valor correspondiente a través de la url (...?ids=ids_tracks), ej: ...?ids=7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B ";
            }
        ?>
    </body>
</html>
