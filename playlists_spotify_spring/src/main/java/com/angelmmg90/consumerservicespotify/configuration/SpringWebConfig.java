/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angelmmg90.consumerservicespotify.configuration;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 *
 * @author amacdong
 */

//@Configuration
//Esta anotación indica que esta clase declara uno o más métodos anotados 
//con @Bean, los cuales deben ser procesados por el contenedor de Spring 
//para generar definiciones de los beans y peticiones de esos beans 
//en tiempo de ejecución

//@EnableWebMvc
//Lo siguiente es habilitar los componentes de Spring MVC con sus 
//configuraciones por default

//@ComponentScan 
//Indica en qué paquetes de nuestra aplicación se encuentran los Controllers

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.angelmmg90.consumeservicespotify.controllers")
public class SpringWebConfig {
    
    private static final String PROXY_HOST = "your_proxy";
    private static final int PROXY_PORT = 8080; //your proxy port
    private static final String PROXY_USER = "user_proxy";
    private static final String PROXY_PASSWORD = "pass_proxy";
    private static final String ACCESS_TOKEN = "your_access_token";
 
    private static RestTemplate template;
    
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");  
        return viewResolver;
    }
    
    @Bean
    public static RestTemplate getTemplate() throws IOException
    {
        if (template == null)
        {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(new AuthScope(PROXY_HOST, PROXY_PORT),
                    new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD));
 

            
            
            Header[] h = new Header[3];
            h[0] = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            h[1] = new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ACCESS_TOKEN);
            
            
            List<Header> headers = new ArrayList<>(Arrays.asList(h));
            
            
            HttpClientBuilder clientBuilder = HttpClientBuilder.create();
 
            clientBuilder.useSystemProperties();
            clientBuilder.setProxy(new HttpHost(PROXY_HOST, PROXY_PORT));
            clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            clientBuilder.setDefaultHeaders(headers).build();
            String SAMPLE_URL = "https://api.spotify.com/v1/users/yourUserName/playlists/7HHFd1tNiIFIwYwva5MTNv";
            
            
                    
            HttpUriRequest request = RequestBuilder.get().setUri(SAMPLE_URL).build();
             
            clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
 
            CloseableHttpClient client = clientBuilder.build();
            client.execute(request);
            
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setHttpClient(client);
 
            template = new RestTemplate();
            template.setRequestFactory(factory);
        }
 
        return template;
    }
    
    //Meter aquí el bean de restClient @bean
   /*@Bean
    public Playlist getByID() throws IOException {
        String id = "7HHFd1tNiIFIwYwva5MTNv";
        
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.spotify.com/v1/users/yourUserName/");
        final URI uri = builder.path("playlists/{playlist_id}").build().expand(id).encode().toUri();
        final ResponseEntity<Playlist> response = getTemplate().getForEntity(uri, Playlist.class);
 
        return response.getBody();
        
    }*/
    
}
