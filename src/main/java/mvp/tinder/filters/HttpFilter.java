package mvp.tinder.filters;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (isHttp(req)) {
            filterChain.doFilter(req, resp);
        } else {
            log.warn("ServletRequest must be instance of HttpServletRequest");
            throw new IllegalArgumentException();
        }
    }

    private boolean isHttp(ServletRequest servletRequest) {
        return servletRequest instanceof HttpServletRequest;
    }

    @Override
    public void destroy() {

    }
}
