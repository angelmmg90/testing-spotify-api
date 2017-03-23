package com.angelmmg90.consumerservicespotify.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author amacdong
 */
public class SpringWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //Similar a AnnotationConfigApplicationContext
        //nos ayuda a inicializar los beans de las clases anotadas con cualquiera de los estereotipos de Spring
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        
        //Le indicamos a AnnotationConfigWebApplicationContext cuáles son las clases que cuentan con las anotaciones que debe procesar
        context.register(SpringWebConfig.class);
        //También indicamos la clase que será el ServletContext 
        context.setServletContext(servletContext);
        
        
        //Registramos el DispatcherServlet dentro de nuestro ServletContext,
        //para esto usamos el método "addServlet" del ServleContext que 
        //recibimos como parámetro. "addServlet" recibe dos parámetros
            //el primero es el nombre del Servlet 
            //el segundo parámetro es la instancia del DispatcherServlet 
                //que recibirá las peticiones de los usuarios. 
        //Para crear la instancia de este DispatcherServlet es necesario 
        //pasar el ApplicationContext que contiene las definiciones de los 
        //controladores.

        //El método "addServlet" regresa una instancia de 
        //"ServletRegistration.Dynamic" que nos ayudará a terminar de 
        //configurar el DispatcherServlet. En esta instancia indicamos que 
        //este debe ser el primer Servlet en ejecutarse cuando se inicie la 
        //aplicación y que todas las peticiones que lleguen a la aplicación 
        //deben pasar a través de él
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("spring-web", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
    
}
