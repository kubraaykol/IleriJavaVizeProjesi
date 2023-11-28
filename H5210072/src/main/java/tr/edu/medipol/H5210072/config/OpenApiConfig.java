package tr.edu.medipol.H5210072.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "H5210072",
                        email = "kubra.aykol@std.medipol.edu.tr",
                        url = "https://medipol.edu.tr"
                ),
                description = "Backend project documentation",
                title = "İleri Java Vize Ödevi",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Localhost",
                        url = "http://localhost:8080"
                )
        }

)
public class OpenApiConfig {
}