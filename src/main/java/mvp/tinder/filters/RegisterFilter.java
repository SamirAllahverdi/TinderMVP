package mvp.tinder.filters;

import mvp.tinder.entity.User;
import mvp.tinder.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class RegisterFilter implements Filter {
    private final UserService userService;

    public RegisterFilter() {
        this.userService = new UserService();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            String username = req.getParameter("username");
            Optional<User> by = userService.getBy(a -> a.getUsername().equals(username));

            if (!by.isPresent()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                try (OutputStream os = resp.getOutputStream()) {
                    Files.copy(Paths.get("src/main/resources/templates/warn.html"), os);
                }
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
