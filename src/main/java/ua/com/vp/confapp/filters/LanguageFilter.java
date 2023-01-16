package ua.com.vp.confapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "defaultLanguage", value = "en")})
public class LanguageFilter implements Filter {
    private static final String LANGUAGE = "language";
    private String defaultLanguage;

    @Override
    public void init(FilterConfig config) {
        defaultLanguage = config.getInitParameter("defaultLanguage");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String language = httpRequest.getParameter(LANGUAGE);

        if (language != null) {
            session.setAttribute(LANGUAGE, language);
        } else {
            String sessionLanguage = (String) session.getAttribute(LANGUAGE);
            session.setAttribute(LANGUAGE, sessionLanguage != null ? sessionLanguage : defaultLanguage);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
