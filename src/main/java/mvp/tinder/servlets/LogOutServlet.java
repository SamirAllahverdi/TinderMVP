package mvp.tinder.servlets;

import mvp.tinder.service.CookiesService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CookiesService cookiesService = new CookiesService(req, resp);
        cookiesService.removeCookie();
        resp.sendRedirect("/login");
    }
}
