package com.angelmmg90.userprofilespotifyvertx.controllers;

import com.angelmmg90.userprofilespotifyvertx.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;

/**
 *
 * @author amacdong
 */
public class UserProfileController extends AbstractVerticle{
    
    private static final String PROXY_HOST = "your_proxy";
    private static final int PROXY_PORT = 8080; //your proxy port
    private static final String ACCESS_TOKEN = "your access token";
    private static final String URL = "https://api.spotify.com/v1/me";
    
    @Override
    public void start() throws UnirestException {
        Unirest.setProxy(new HttpHost(PROXY_HOST, PROXY_PORT));
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                        = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {

            HttpResponse<JsonNode> userJsonResponse;
            try {
                
                
                // Response to Object
                HttpResponse<User> userObjectResponse = Unirest.get(URL)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json" )
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+ACCESS_TOKEN)
                        .asObject(User.class);  
                
                System.out.println(userObjectResponse.getBody().getDisplay_name());
                userJsonResponse = Unirest.get(URL)
                       .header(HttpHeaders.CONTENT_TYPE, "application/json" )
                       .header(HttpHeaders.AUTHORIZATION, "Bearer "+ACCESS_TOKEN)
                       .asJson();
                
                System.out.println(userJsonResponse.getBody().toString());
            } catch (UnirestException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

                
        });
        
        server.requestHandler(router::accept).listen(8080);
        
    }
    
    
}
