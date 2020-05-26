package mvp.tinder.servlets;

import mvp.tinder.entity.User;
import mvp.tinder.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get("src/main/resources/templates/register.ftl"), os);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String url_photo = req.getParameter("url_photo");
        String job = req.getParameter("job");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(0, name, surname, url_photo, job, username, password, "", 0);
        userService.put(0, user);

        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get("src/main/resources/templates/success.html"), os);
        }
    }
}
