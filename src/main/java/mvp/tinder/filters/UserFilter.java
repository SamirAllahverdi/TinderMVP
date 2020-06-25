package mvp.tinder.filters;

import mvp.tinder.entity.User;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserFilter implements Filter {
    private final UserService userService;

    public UserFilter() {
        this.userService = new UserService();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            String a = req.getParameter("choice");
            if (a.equals("NO")) {
                resp.sendRedirect("/users");
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            CookiesService cookiesService = new CookiesService(req, resp);
            Cookie id = cookiesService.getCookies();
            int actualID = Integer.parseInt(id.getValue());
            User user = userService.get(actualID);
            if (user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                try (OutputStream os = resp.getOutputStream()) {
                    Files.copy(Paths.get("src/main/resources/templates/clear.ftl"), os);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

}
