package nttdata.bootcamp.quarkus.client.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

public class ExternalProperties {

    @ConfigProperty(name = "prueba.message")
    String message;

    @ConfigProperty(name = "prueba.codigo")
    String codigo;

}
