package mvp.tinder.servlets;

import mvp.tinder.entity.User;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get("src/main/resources/templates/login.ftl"), os);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        CookiesService cookiesService = new CookiesService(req, resp);
        Optional<User> userO = userService.getBy(b -> b.getUsername().equals(username) && b.getPassword().equals(password));

        User user = userO.orElse(null);
        int a = user.getId();
        cookiesService.addCookie(a);
        userService.update(a);
        resp.sendRedirect("/users");
    }
}

