package mvp.tinder.servlets;

import mvp.tinder.entity.User;
import mvp.tinder.helper.TemplateEngine;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.LikedService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {

    public final TemplateEngine engine;
    private final LikedService likedService;

    public LikedServlet(TemplateEngine engine) {
        this.engine = engine;
        this.likedService = new LikedService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie cookie = cookiesService.getCookies();
        int actualID = Integer.parseInt(cookie.getValue());

        List<User> liked = likedService.getAll(actualID, 0);

        HashMap<String, Object> data = new HashMap<>();
        data.put("likedProfiles", liked);
        engine.render("people-list.ftl", data, resp);
    }
}

