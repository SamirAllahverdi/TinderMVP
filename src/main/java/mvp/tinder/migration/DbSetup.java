package mvp.tinder.migration;

import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

@Log4j2
public class DbSetup {

    public static void migrate(String url, String user, String passwd) {
        justDoIt(url, user, passwd, false);
        log.info("Database migrated");
    }

    public static void clearAndMigrate(String url, String user, String passwd) {
        justDoIt(url, user, passwd, true);
        log.info("Database cleared and migrated");
    }

    private static void justDoIt(String url, String user, String passwd, boolean clean) {
        FluentConfiguration config = new FluentConfiguration().dataSource(url, user, passwd);
        Flyway flyway = new Flyway(config);
        if (clean) flyway.clean();
        flyway.migrate();
    }
}
