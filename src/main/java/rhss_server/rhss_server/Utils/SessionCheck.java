package rhss_server.rhss_server.Utils;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;

public class SessionCheck {

    public String username;
    public boolean admin;
    public boolean administrativo;

    public SessionCheck (HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean admin = (Boolean) session.getAttribute("admin");
        boolean administrativo = (Boolean) session.getAttribute("administrativo");
        if(username != null) {
            this.admin = admin;
            this.administrativo = administrativo;
            this.username = username;
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }

    }
    
}
