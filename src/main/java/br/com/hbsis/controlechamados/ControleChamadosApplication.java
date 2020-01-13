package br.com.hbsis.controlechamados;

import br.com.hbsis.controlechamados.config.ControleChamadosApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties(ControleChamadosApiProperty.class)
public class ControleChamadosApplication {

        public static void main(String[] args) {
            SpringApplication.run(ControleChamadosApplication.class, args);

        }
}
