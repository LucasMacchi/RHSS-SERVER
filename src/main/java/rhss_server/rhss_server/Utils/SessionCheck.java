package rhss_server.rhss_server.Utils;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;

public class SessionCheck {

    public String username;
    public boolean admin;
    public boolean administrativo;
    public String empresa;

    public SessionCheck (HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean admin = (Boolean) session.getAttribute("admin");
        boolean administrativo = (Boolean) session.getAttribute("administrativo");
        String empresa_id = (String) session.getAttribute("empresa");
        System.out.println(username);
        if(username != null) {
            this.admin = admin;
            this.administrativo = administrativo;
            this.username = username;
            this.empresa = empresa_id;
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401),"No autorizado.");
        }
        
    }

    public void checkAdmin () {
        if(!this.admin) throw new ResponseStatusException(HttpStatusCode.valueOf(401),"No autorizado.");
    }
    
}
