package rhss_server.rhss_server.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${FRONT_URL}")
    private String frontURL;
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("CORS "+frontURL);
        registry.addMapping("/**")
                .allowedOrigins(frontURL)
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);           
    }

    public WebConfig(SessionInterceptor sessionInterceptor) {
        this.sessionInterceptor = sessionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**") // Aplicar a todas las rutas
                .excludePathPatterns("/usuario/login"); // Excluir login
    }


}