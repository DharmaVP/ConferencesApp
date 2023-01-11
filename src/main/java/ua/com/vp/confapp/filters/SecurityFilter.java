package ua.com.vp.confapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.CommandException;


import java.io.IOException;

import static ua.com.vp.confapp.command.constants.Parameters.ROLE_GUEST;
import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"FrontController"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String servletPath = req.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserDTO loginedUser = AppUtils.getLoginedUser(req.getSession());

        HttpServletRequest wrapRequest = req;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getEmail();

            // Роли (Role).
            String role = loginedUser.getRole();

            // Старый пакет request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, role, req);
        }

        // Страницы требующие входа в систему.
        try {
            if (SecurityUtils.isSecurityCommand(req)) {

                // Если пользователь еще не вошел в систему,
                // Redirect (перенаправить) к странице логина.
                if (loginedUser == null) {

                    String requestUri = req.getRequestURI();

                    // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
                    int redirectId = AppUtils.storeRedirectAfterLoginUrl(req.getSession(), requestUri);

                    resp.sendRedirect(wrapRequest.getContextPath() + "/controller?action=get_sign_in_page&redirectId=" + redirectId);
                    return;
                }

                // Проверить пользователь имеет действительную роль или нет?
                boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
                if (!hasPermission) {

                    RequestDispatcher dispatcher //
                            = request.getServletContext().getRequestDispatcher("/WEB-INF/common/accessDeniedView.jsp");

                    dispatcher.forward(request, response);
                    return;
                }
            }
        } catch (CommandException e) {
            RequestDispatcher dispatcher //
                    = request.getServletContext().getRequestDispatcher("/WEB-INF/common/error.jsp");

            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(wrapRequest, response);
    }
}

