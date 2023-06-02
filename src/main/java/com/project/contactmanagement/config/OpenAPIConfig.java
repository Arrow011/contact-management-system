package com.project.contactmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Value("${openapi.staging-url}")
    private String stagingUrl;

    @Value("${openapi.production-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL for development environment.");

        Server stagingServer = new Server();
        stagingServer.setUrl(stagingUrl);
        stagingServer.setDescription("Server URL for staging environment.");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL for production environment.");

        Contact contact = new Contact();
        contact.setEmail("cms@gmail.com");
        contact.setName("CMS");
        contact.setUrl("https://www.cms.com");

        Info info = new Info()
                .title("Contact Management System API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage contacts.").termsOfService("https://www.cms.com/terms");

        return new OpenAPI().info(info).servers(List.of(devServer, stagingServer, prodServer));
    }
}
