package mvp.tinder.servlets;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.entity.User;
import mvp.tinder.helper.TemplateEngine;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.LikedService;
import mvp.tinder.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

@Log4j2
public class UserServlet extends HttpServlet {
    final TemplateEngine engine;
    private final UserService userService;
    private final LikedService likedService;

    public UserServlet(TemplateEngine engine) {
        this.engine = engine;
        this.userService = new UserService();
        this.likedService = new LikedService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie id = cookiesService.getCookies();
        int actualID = Integer.parseInt(id.getValue());

        User user = userService.get(actualID);
        log.debug("DoGet" + user);

        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("surname", user.getSurname());
        data.put("picture", user.getPicture());
        engine.render("like-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie id = cookiesService.getCookies();
        int actualID = Integer.parseInt(id.getValue());
        String getId = req.getParameter("id");
        Optional<User> user = userService.getBy(a -> a.getId() == Integer.parseInt(getId));
        log.debug("DoPost" + user);
        likedService.put(actualID, user.orElse(null));
        resp.sendRedirect("/users");
    }
}