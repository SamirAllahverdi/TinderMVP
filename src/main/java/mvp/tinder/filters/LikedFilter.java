package mvp.tinder.filters;

import mvp.tinder.entity.User;
import mvp.tinder.service.CookiesService;
import mvp.tinder.service.LikedService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LikedFilter implements Filter {
    private final LikedService likedService;

    public LikedFilter() {
        this.likedService = new LikedService();
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        CookiesService cookiesService = new CookiesService(req, resp);
        Cookie cookie = cookiesService.getCookies();
        int actualID = Integer.parseInt(cookie.getValue());

        List<User> liked = likedService.getAll(actualID, 0);

        if (!liked.isEmpty()) {
            filterChain.doFilter(req, resp);
        } else {
            try (OutputStream os = resp.getOutputStream()) {
                Files.copy(Paths.get("src/main/resources/templates/inform.html"), os);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
