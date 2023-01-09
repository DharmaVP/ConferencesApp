package ua.com.vp.confapp.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;


@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private String encodingParam;

    @Override
    public void init(FilterConfig filterConfig) {
        encodingParam = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encodingParam);
        servletResponse.setCharacterEncoding(encodingParam);
        servletResponse.setContentType("text/html; charset=" + encodingParam);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
