package ua.com.vp.confapp.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.security.Principal;

/**
 * An extension for the HTTPServletRequest that overrides the getUserPrincipal()
 * and isUserInRole(). We supply these implementations here, where they are not
 * normally populated unless we are going through the facility provided by the
 * container.
 * <p>
 * If he user or roles are null on this wrapper, the parent request is consulted
 * to try to fetch what ever the container has set for us. This is intended to
 * be created and used by the UserRoleFilter.
 *
 * @author thein
 *
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private String role;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, String role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String currentRole) {
        if (currentRole == null) {
            return this.realRequest.isUserInRole(currentRole);
        }
        return role.equals(currentRole);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

        // Make an anonymous implementation to just return our user
        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }
}
