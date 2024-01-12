package br.com.vaidaruim.gs3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI geraDocumentacao() {
        Server devServer = new Server();
        devServer.setUrl("https://medicamixbackendwebapp.azurewebsites.net");
//        devServer.setUrl("http://localhost:8080/api/farmacos");
        devServer.description("Url de desenvolvimento azure");

        Contact contact = new Contact();
        contact.setEmail("rm97373@fiap.com.br");
        contact.setName("Raquel");

        Info info = new Info().title("GS3 - Vai dar ruim?")
                .version("0.1")
                .contact(contact)
                .description("App que cruza possíveis riscos da mistura de fármacos");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
