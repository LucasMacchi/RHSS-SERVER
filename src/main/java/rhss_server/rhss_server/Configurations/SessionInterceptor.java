package rhss_server.rhss_server.Configurations;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (uri.startsWith("/usuario/login/")) {
            return true; // No chequear sesión para /login
        }
        if (uri.startsWith("/usuario/logout")) {
            return true; // No chequear sesión para /login
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            System.out.println("SESSION NOT VALIDATED FALSE");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sesión no válida.");
            return false;
        }
        return true; // Sesión válida
    }
}
