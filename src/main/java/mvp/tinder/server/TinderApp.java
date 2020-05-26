package mvp.tinder.server;

import mvp.tinder.filters.*;
import mvp.tinder.helper.TemplateEngine;
import mvp.tinder.migration.DbSetup;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import mvp.tinder.servlets.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TinderApp {
    public static void main(String[] args) throws Exception {
        DbSetup.migrate();

        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

        TemplateEngine engine = TemplateEngine.resources("/templates");

        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/messages/css/*");

        handler.addServlet(new ServletHolder(new UserServlet(engine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(engine)),"/messages/*");

        handler.addServlet(LoginServlet.class, "/login");
        handler.addServlet(RegisterServlet.class, "/register");
        handler.addServlet(LogOutServlet.class, "/logout");
        handler.addServlet(ClearServlet.class, "/clear");

        handler.addServlet(RedirectServlet.class, "/*");

        handler.addFilter(CookieFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(CookieFilter.class, "/liked", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(CookieFilter.class, "/messages/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(CookieFilter.class, "/clear", EnumSet.of(DispatcherType.REQUEST));

        handler.addFilter(LoginFilter.class, "/login", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(UserFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(LikedFilter.class, "/liked", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(MessageFilter.class, "/messages/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(RegisterFilter.class, "/register", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
