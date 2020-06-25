package mvp.tinder.servlets;

import mvp.tinder.helper.HerokuEnv;
import mvp.tinder.migration.DbSetup;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DbSetup.clearAndMigrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
        resp.sendRedirect("/users");
    }
}
