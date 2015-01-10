package doyle.foo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by doyle on 1/8/15.
 */
public class NotificationApp extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new NotificationApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<AppConfig> appConfigBootstrap) {

    }

    @Override
    public void run(AppConfig configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template" , healthCheck);
        environment.jersey().register(resource);
    }
}
