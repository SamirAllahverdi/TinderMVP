package mvp.tinder.filters;

import mvp.tinder.entity.User;
import mvp.tinder.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class LoginFilter implements Filter {
    private final UserService userService;

    public LoginFilter() {
        this.userService = new UserService();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getMethod().equals("POST")) {
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
            Optional<User> user = userService.getBy(b -> b.getUsername().equals(username) && b.getPassword().equals(password));

            if (user.isPresent()) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                try (OutputStream os = servletResponse.getOutputStream()) {
                    Files.copy(Paths.get("src/main/resources/templates/warn2.html"), os);
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
