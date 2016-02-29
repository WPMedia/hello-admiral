package com.washingtonpost.helloadmiral;

import com.washingtonpost.helloadmiral.resources.HelloAdmiralResource;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class HelloAdmiralApplication extends Application<com.washingtonpost.helloadmiral.HelloAdmiralConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloAdmiralApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloAdmiralConfiguration> bootstrap) {
        bootstrap.addBundle(new TemplateConfigBundle());
    }

    @Override
    public void run(HelloAdmiralConfiguration configuration, Environment environment) {
        environment.servlets().addFilter("Custom-Filter-Name", new CORSFilter()).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        final HelloAdmiralResource resource = new HelloAdmiralResource(configuration.getArcAuthUri(), configuration.getArcAuthUser(), configuration.getArcAuthPass());
        environment.jersey().register(resource);
    }
}
