package starter.pack.restapi;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import starter.pack.restapi.config.DependencyConfig;
import starter.pack.restapi.config.RestApiConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));

        DispatcherServlet restApiServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic restApiDispatcher = servletContext.addServlet("rest-api-dispatcher", restApiServlet);
        restApiDispatcher.setLoadOnStartup(1);
        restApiDispatcher.addMapping("/*");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext annotationContext = new AnnotationConfigWebApplicationContext();
        annotationContext.register(DependencyConfig.class, RestApiConfig.class);
        return annotationContext;
    }
}
