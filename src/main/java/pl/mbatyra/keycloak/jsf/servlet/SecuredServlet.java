package pl.mbatyra.keycloak.jsf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

/**
 *
 * @author Mariusz Batyra
 */
public class SecuredServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SecuredServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.log(Level.INFO, "Access to secured servlet granted!");
        
        AccessToken accessToken = ((KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName())).getToken();

        String issuer = accessToken.getIssuer();
        String username = accessToken.getPreferredUsername();

        LOG.log(Level.INFO, "Token Issuer: {0}", issuer);
        LOG.log(Level.INFO, "username: {0}", username);
        
        PrintWriter out = response.getWriter();
        out.print("Content of secured servlet\nUsername: " + username);
    }

}