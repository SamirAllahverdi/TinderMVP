package mvp.tinder.servlets;

import lombok.SneakyThrows;
import mvp.tinder.entity.Message;
import mvp.tinder.entity.User;
import mvp.tinder.helper.TemplateEngine;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.MessagesService;
import mvp.tinder.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class MessageServlet extends HttpServlet {
    public final TemplateEngine engine;
    private final MessagesService messagesService;
    private final UserService userService;

    public MessageServlet(TemplateEngine engine) {
        this.messagesService = new MessagesService();
        this.engine = engine;
        this.userService = new UserService();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String userIdFromParam = req.getPathInfo().replace("/", "");
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie cookie = cookiesService.getCookies();
        int activeUser = Integer.parseInt(cookie.getValue());

        List<Message> messages = messagesService.getAll(activeUser, Integer.parseInt(userIdFromParam.replace("/", "")));
        User who = userService.getBy(c -> c.getId() == activeUser).orElse(null);
        User whom = userService.getBy(c -> c.getId() == Integer.parseInt(userIdFromParam.replace("/", ""))).orElse(null);

        HashMap<String, Object> data = new HashMap<>();
        data.put("who", who);
        data.put("whom", whom);
        data.put("messages", messages);

        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("text");
        String userIdFromParam = req.getPathInfo().replace("/", "");
        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie cookie = cookiesService.getCookies();
        int activeUser = Integer.parseInt(cookie.getValue());

        Message message = new Message(activeUser,
                Integer.parseInt(userIdFromParam.replace("/", "")),
                text,
                "today");

        messagesService.put(activeUser, message);
        resp.sendRedirect("/messages/" + userIdFromParam);
    }
}
