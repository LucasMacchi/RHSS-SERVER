package rhss_server.rhss_server.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        //filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(false);
        filter.setIncludeClientInfo(true);
        filter.setBeforeMessagePrefix("ROUTE REQUEST: ");
        filter.setAfterMessagePrefix("ROUTE REQUESTED: ");
        return filter;
    }
    
}
