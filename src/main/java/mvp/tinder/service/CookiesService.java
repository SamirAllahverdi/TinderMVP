package mvp.tinder.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookiesService {

    private final String COOKIE_NAME = "activeUser";
    private HttpServletResponse resp;
    private HttpServletRequest req;


    public CookiesService(HttpServletRequest req, HttpServletResponse resp) {
        this.resp = resp;
        this.req = req;
    }

    public Cookie getCookies() {
        Cookie result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    result = c;
                }
            }
        }
        return result;
    }

    public void addCookie(int id) {
        resp.addCookie(new Cookie(COOKIE_NAME, String.valueOf(id)));
    }

    public void removeCookie() {
        Arrays.stream(req.getCookies()).filter(c -> c.getName().
                equalsIgnoreCase(COOKIE_NAME)).
                map(a -> new Cookie(a.getName(), a.getValue()) {{
                    setMaxAge(0);
                }}).forEach(resp::addCookie);
    }
}
