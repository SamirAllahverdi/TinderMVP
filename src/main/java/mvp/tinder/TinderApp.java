package mvp.tinder;

import lombok.extern.log4j.Log4j2;
import mvp.tinder.filters.*;
import mvp.tinder.helper.HerokuEnv;
import mvp.tinder.helper.TemplateEngine;
import mvp.tinder.migration.DbSetup;
import mvp.tinder.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Log4j2
public class TinderApp {
    private static final EnumSet<DispatcherType> DT = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
        DbSetup.migrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());

        Server server = new Server(HerokuEnv.port());
        log.info("server started");

        ServletContextHandler handler = new ServletContextHandler();

        TemplateEngine engine = TemplateEngine.resources("/templates");

        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/messages/css/*");

        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(engine)), "/messages/*");

        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(RegisterServlet.class, "/register");
        handler.addServlet(LogOutServlet.class, "/logout");
        handler.addServlet(ClearServlet.class, "/clear");
        handler.addServlet(RedirectServlet.class, "/*");

        handler.addFilter(HttpFilter.class, "/*", DT);

        handler.addFilter(CookieFilter.class, "/users", DT);
        handler.addFilter(CookieFilter.class, "/liked", DT);
        handler.addFilter(CookieFilter.class, "/messages/*", DT);
        handler.addFilter(CookieFilter.class, "/clear", DT);
        handler.addFilter(CookieFilter.class, "/login", DT);
        handler.addFilter(CookieFilter.class, "/logout", DT);

        handler.addFilter(LoginFilter.class, "/login", DT);
        handler.addFilter(UserFilter.class, "/users", DT);
        handler.addFilter(LikedFilter.class, "/liked", DT);
        handler.addFilter(MessageFilter.class, "/messages/*", DT);
        handler.addFilter(RegisterFilter.class, "/register", DT);

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
