package mvp.tinder.filters;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.MessagesService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class MessageFilter implements Filter {

    public MessageFilter() {
        MessagesService messagesService = new MessagesService();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (((HttpServletRequest) servletRequest).getMethod().equals("POST")) {

            String userIdFromParam = req.getPathInfo().replace("/", "");
            String text = req.getParameter("text");

            if (text.equals("")) {
                resp.sendRedirect("/messages/" + Integer.parseInt(userIdFromParam));
            } else {
                filterChain.doFilter(req, resp);
            }

        } else {
            CookiesService cookiesService = new CookiesService(req, resp);
            String userIdFromParam = req.getPathInfo().replace("/", "");

            if (messageToYourself(cookiesService, userIdFromParam)) {
                try (PrintWriter w = servletResponse.getWriter()) {
                    log.warn("don't try to send message to yourself");
                    w.write("Sorry, you can not send message to yourself");
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean messageToYourself(CookiesService cookiesService, String receiverId) {
        Cookie cookie = cookiesService.getCookies();
        return cookie.getValue().equals(receiverId);
    }
}

